package com.demo.service.base;

import com.demo.entity.base.BaseEntity;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Service接口父类
 *
 * @param <T> 对象
 * @param <ID> 主键
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    /**
     * 增加
     *
     * @param t t
     * @return int
     */
    int createEntity(T t);

    /**
     * 更新
     *
     * @param t t
     * @return int
     */
    int updateEntity(T t);

    /**
     * 根据ID删除对象
     *
     * @param id id
     * @return int
     */
    int deleteById(ID id);

    /**
     * 根据ID数组删除对象
     *
     * @param ids i
     * @return int
     */
    int deleteByIds(ID[] ids);

    /**
     * 根据实体删除对象
     *
     * @param t t
     * @return int
     */
    int deleteByObject(T t);

    /**
     * 根据id找到对象
     *
     * @param id id
     * @return T
     */
    T queryById(ID id);

    /**
     * 根据参数对象查询对象
     *
     * @param t t
     * @return T
     */
    T queryByEntity(T t);

    /**
     * 根据Map查询对象
     *
     * @param map map
     * @return T
     */
    T queryByMap(Map<String, String> map);

    /**
     * 查询所有数据列表
     *
     * @return List<T>
     */
    List<T> queryList();

    /**
     * 根据实体来查询List列表
     *
     * @param t t
     * @return List<T>
     */
    List<T> queryListByEntity(T t);

    /**
     * 根据Map参数来查询
     *
     * @param map map
     * @return List<T>
     */
    List<T> queryListByMap(Map<String, String> map);

    /**
     * 分页查询
     *
     * @param t t
     * @param currenPage cp
     * @return PageInfo<T>
     */
    PageInfo<T> pageQueryEntity(T t, int currenPage, int pageSize);

    /**
     * 查询统计 - 无参数
     *
     * @return long
     */
    long pageQueryEntityCount();

    /**
     * 查询统计 - 对象查询
     *
     * @param t t
     * @return long
     */
    long pageQueryEntityCount(T t);

    /**
     * 执行原生sql
     *
     * @param sql sql
     * @param parameMap p
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> executeSql(String sql, Map<String, Object> parameMap);

}
