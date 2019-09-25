package com.han.my.shop.web.admin.dao;

import com.han.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by han on 2019/9/8.
 */
@Repository
public interface TbContentDao {
    //查询所有
    public List<TbContent> selectAll();

    //插入单个
    public void insert(TbContent tbContent);

    //根据id删除
    public void deleteById(Long id);

    //根据id查询
    public TbContent getById(Long id);

    //修改用户
    public  void update(TbContent tbContent);

    //批量删除
    void deleteMulti(String[] ids);

    //分页查询
    List<TbContent> page(Map<String,Object> params);

    //查询总数
    int count(TbContent tbContent);
}
