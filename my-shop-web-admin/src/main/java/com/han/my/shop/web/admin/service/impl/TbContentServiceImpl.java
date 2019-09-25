package com.han.my.shop.web.admin.service.impl;

import com.han.my.shop.commons.dto.BaseResult;
import com.han.my.shop.commons.dto.PageInfo;
import com.han.my.shop.commons.validator.BeanValidator;
import com.han.my.shop.domain.TbContent;
import com.han.my.shop.web.admin.dao.TbContentDao;
import com.han.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by han on 2019/9/8.
 */
@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        if(validator!=null){
            return BaseResult.fail(validator);
        }else{
            //通过验证
                tbContent.setUpdated(new Date());
                if(tbContent.getId()==null){
                    //新增
                    tbContent.setCreated(new Date());
                    tbContentDao.insert(tbContent);
                }else{
                    //修改
                    tbContentDao.update(tbContent);
                }
                return BaseResult.success("保存成功");
            }
    }

    @Override
    public void deleteById(Long id) {
        tbContentDao.deleteById(id);
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbContent",tbContent);

        PageInfo<TbContent> pageInfo=new PageInfo<TbContent>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContentDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }

}
