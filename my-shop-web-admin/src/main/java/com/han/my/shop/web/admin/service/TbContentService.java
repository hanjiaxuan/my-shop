package com.han.my.shop.web.admin.service;

import com.han.my.shop.commons.dto.BaseResult;
import com.han.my.shop.commons.dto.PageInfo;
import com.han.my.shop.domain.TbContent;

import java.util.List;

/**
 * Created by han on 2019/9/8.
 */
public interface TbContentService {
    //查询所有
    public List<TbContent> selectAll();

    //插入单个
    public BaseResult save(TbContent tbContent);

    //根据id删除
    public void deleteById(Long id);

    //根据id查询
    public TbContent getById(Long id);

    //修改用户
    public  void update(TbContent tbContent);

    //批量删除
    void deleteMulti(String[] ids);

    //分页查询
    PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    //查询总数
    int count(TbContent tbContent);
}
