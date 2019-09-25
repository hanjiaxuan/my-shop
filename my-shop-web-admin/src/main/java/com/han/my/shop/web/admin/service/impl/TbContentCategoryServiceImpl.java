package com.han.my.shop.web.admin.service.impl;

import com.han.my.shop.domain.TbContentCategory;
import com.han.my.shop.web.admin.dao.TbContentCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by han on 2019/9/8.
 */
@Service
public class TbContentCategoryServiceImpl implements com.han.my.shop.web.admin.service.TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }
}
