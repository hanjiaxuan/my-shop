package com.han.my.shop.web.admin.service;

import com.han.my.shop.commons.dto.BaseResult;
import com.han.my.shop.commons.dto.PageInfo;
import com.han.my.shop.domain.TbUser;

import java.util.List;

/**
 * Created by han on 2019/9/3.
 */
public interface TbUserService {
    //查询所有用户
    public List<TbUser> selectAll();
    //插入单个用户
    public BaseResult save(TbUser tbUser);
    //根据id删除
    void deleteById(Long id);
    //根据id查询
    TbUser getById(Long id);
    //更新用户
    void update(TbUser tbUser);
    //根据用户名查询
    /*List<TbUser> selectByUsername(String username);*/
    //用户登录
    TbUser login(String email,String password);
    //条件查询
    /*List<TbUser> search(TbUser tbUser);*/
    //批量删除
    void deleteMulti(String[] ids);
    //分页查询
    PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);
    //查询总数
    int count(TbUser tbUser);
}
