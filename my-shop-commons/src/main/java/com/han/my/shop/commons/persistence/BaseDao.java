package com.han.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * Created by han on 2019/9/10.
 */
public interface BaseDao<T extends BaseEntity>  {
    //查询所有
    public List<T> selectAll();

    //插入单个
    public void insert(T entity);

    //根据id删除
    public void deleteById(Long id);

    //根据id查询
    public T getById(Long id);

    //修改
    public  void update(T entity);

    //批量删除
    void deleteMulti(String[] ids);

    //分页查询
    List<T> page(Map<String,Object> params);

    //查询总数
    int count(T entity);
}
