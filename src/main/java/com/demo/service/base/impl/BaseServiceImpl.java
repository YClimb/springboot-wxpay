package com.demo.service.base.impl;

import com.demo.entity.base.BaseEntity;
import com.demo.mapper.base.BaseMapper;
import com.demo.service.base.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ServiceImpl实现类父类
 *
 * @param <T> 对象
 * @param <ID> 主键
 */
public abstract class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseMapper<T, ID> getMapper();

    public int createEntity(T t) {
        return this.getMapper().createEntity(t);
    }

    public int updateEntity(T t) {
        return this.getMapper().updateEntity(t);
    }

    public int deleteById(ID id) {
        return this.getMapper().deleteById(id);
    }

    public int deleteByIds(ID[] ids) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("ids", ids);
        return this.getMapper().deleteByIds(params);
    }

    public int deleteByObject(T t) {
        return this.getMapper().deleteByObject(t);
    }

    public T queryById(ID id) {
        return this.getMapper().queryById(id);
    }

    public T queryByEntity(T t) {
        if (t == null) {
            return null;
        }
        return this.getMapper().queryByEntity(t);
    }

    public T queryByMap(Map<String, String> map) {
        return this.getMapper().queryByMap(map);
    }

    public List<T> queryList() {
        return queryListByEntity(null);
    }

    public List<T> queryListByEntity(T t) {
        return this.getMapper().queryListByEntity(t);
    }

    public List<T> queryListByMap(Map<String, String> map) {
        return this.getMapper().queryListByMap(map);
    }

    public PageInfo<T> pageQueryEntity(T t, int currenPage, int pageSize) {
        PageHelper.startPage(currenPage, pageSize, false);
        Page<T> page = this.getMapper().pageQueryEntity(t);
        page.setTotal(this.getMapper().pageQueryEntityCount(t));
        return new PageInfo<T>(page);
    }

    public long pageQueryEntityCount() {
        return pageQueryEntityCount(null);
    }

    public long pageQueryEntityCount(T t) {
        return this.getMapper().pageQueryEntityCount(t);
    }

    public List<Map<String, Object>> executeSql(String sql, Map<String, Object> parameMap) {
        parameMap.put("sql", sql);
        return getMapper().executeSql(parameMap);
    }

}
