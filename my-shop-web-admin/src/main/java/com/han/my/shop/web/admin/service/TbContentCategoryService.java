package com.han.my.shop.web.admin.service;

import com.han.my.shop.domain.TbContentCategory;

import java.util.List;

/**
 * Created by han on 2019/9/8.
 */
public interface TbContentCategoryService {
    List<TbContentCategory> selectAll();
    List<TbContentCategory> selectByPid(Long pid);
}
