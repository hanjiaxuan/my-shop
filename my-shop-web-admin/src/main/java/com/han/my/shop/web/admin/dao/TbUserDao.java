package com.han.my.shop.web.admin.dao;

import com.han.my.shop.commons.persistence.BaseDao;
import com.han.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by han on 2019/9/3.
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    //根据邮箱查询
    public TbUser getByEmail(String email);

}
