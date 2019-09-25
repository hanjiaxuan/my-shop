package com.han.my.shop.web.admin.service.test;

import com.han.my.shop.domain.TbUser;
import com.han.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by han on 2019/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("yasuo");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
        tbUser.setPhone("13100001111");
        tbUser.setEmail("yasuo@qq.com");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.deleteById(39l);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(36l);
        System.out.println("用户名是："+tbUser.getUsername());
    }

    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(36l);
        tbUser.setUsername("yasuo");
        tbUserService.update(tbUser);
    }

    /*@Test
    public void testSelectByUsername(){
        List<TbUser> tbUsers = tbUserService.selectByUsername("san");
        if (tbUsers.size()>0){
            for (TbUser tbUser : tbUsers) {
                System.out.println(tbUser.getUsername());
            }
        }
    }*/

}
