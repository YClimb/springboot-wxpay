package com.demo.mapper.base;

import com.demo.entity.base.BaseEntity;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Mapper父类
 *
 * @param <T> 对象
 * @param <ID> 主键
 */
public interface BaseMapper<T extends BaseEntity, ID extends Serializable> {

	/**
	 * 增加
	 * 
	 * @param t t
	 * @return int
	 */
	public int createEntity(T t);

	/**
	 * 更新
	 * 
	 * @param t t
	 * @return int
	 */
	public int updateEntity(T t);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id id
	 * @return int
	 */
	public int deleteById(ID id);

	/**
	 * 根据ID数组删除对象
	 * 
	 * @param params p
	 * @return int
	 */
	public int deleteByIds(Map<String, Object> params);

	/**
	 * 根据实体删除对象
	 * 
	 * @param t t
	 * @return int
	 */
	public int deleteByObject(T t);

	/**
	 * 根据id找到对象
	 * 
	 * @param id id
	 * @return T
	 */
	public T queryById(ID id);

	/**
	 * 根据参数对象查询对象
	 * 
	 * @param t t
	 * @return T
	 */
	public T queryByEntity(T t);

	/**
	 * 根据Map查询对象
	 * 
	 * @param map m
	 * @return T
	 */
	public T queryByMap(Map<String, String> map);

	/**
	 * 查询所有的出列表
	 *
	 * @param t t
	 * @return List<T>
	 */
	public List<T> queryListByEntity(T t);

	/**
	 * 根据Map参数来查询
	 * 
	 * @param map m
	 * @return List<T>
	 */
	public List<T> queryListByMap(Map<String, String> map);

	/**
	 * 分页查询
	 *
	 * @param t t
	 * @return Page<T>
	 */
	public Page<T> pageQueryEntity(T t);

	/**
	 * 查询统计
	 *
	 * @param t t
	 * @return long
	 */
	public long pageQueryEntityCount(T t);

	/**
	 * 执行原生sql
	 * @param parameMap 参数
	 * @return
	 */
	public List<Map<String, Object>> executeSql(Map<String, Object> parameMap);

}
