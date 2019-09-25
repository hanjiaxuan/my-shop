package com.han.my.shop.web.admin.service.impl;

import com.han.my.shop.commons.dto.BaseResult;
import com.han.my.shop.commons.dto.PageInfo;
import com.han.my.shop.commons.validator.BeanValidator;
import com.han.my.shop.domain.TbUser;
import com.han.my.shop.web.admin.dao.TbUserDao;
import com.han.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import utils.RegexpUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by han on 2019/9/3.
 */
@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        if (validator!=null){
            return BaseResult.fail(validator);
        }else{
            tbUser.setUpdated(new Date());
            if(tbUser.getId()==null){
                //新增
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }else{
                //修改
                tbUserDao.update(tbUser);
            }
            return BaseResult.success("保存用户成功");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        tbUserDao.deleteById(id);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

/*    @Override
    public List<TbUser> selectByUsername(String username) {
        return tbUserDao.selectByUsername(username);
    }*/

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser!=null){
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //判断密码是否匹配
            if (md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    //条件查询
   /* @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }*/

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser) {
        int count = tbUserDao.count(tbUser);

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbUser",tbUser);

        PageInfo<TbUser> pageInfo=new PageInfo<TbUser>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    //用户信息验证
    private BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseresult = BaseResult.success();
        //非空验证
        if (StringUtils.isBlank(tbUser.getEmail())){
            baseresult=BaseResult.fail("邮箱不能为空，请重新输入");
        }else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseresult=BaseResult.fail("邮箱格式不正确，请重新输入");
        }else if (StringUtils.isBlank(tbUser.getPassword())){
            baseresult=BaseResult.fail("密码不能为空，请重新输入");
        }else if (StringUtils.isBlank(tbUser.getUsername())){
            baseresult=BaseResult.fail("姓名不能为空，请重新输入");
        }else if (StringUtils.isBlank(tbUser.getPhone())){
            baseresult=BaseResult.fail("手机不能为空，请重新输入");
        }else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseresult=BaseResult.fail("手机格式不正确，请重新输入");
        }
        return baseresult;
    }
}
