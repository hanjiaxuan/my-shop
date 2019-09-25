package com.han.my.shop.domain;

import com.han.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by han on 2019/9/8.
 */
@Data
public class TbContent extends BaseEntity {

        @Length(min = 1,max = 20,message = "标题长度介于1-20字符之间")
        private String title;
        @Length(min = 1,max = 20,message = "子题长度介于1-20字符之间")
        private String subTitle;
        @Length(min = 1,max = 50,message = "标题描述介于1-20字符之间")
        private String titleDesc;
        private String url;
        private String pic;
        private String pic2;
        private String content;
        @NotNull(message = "父级类目不能为空")
        private TbContentCategory tbContentCategory;
}
