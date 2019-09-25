package com.han.my.shop.web.admin.dao;

import com.han.my.shop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by han on 2019/9/8.
 */
@Repository
public interface TbContentCategoryDao {
    List<TbContentCategory> selectAll();
    List<TbContentCategory> selectByPid(Long pid);
}
