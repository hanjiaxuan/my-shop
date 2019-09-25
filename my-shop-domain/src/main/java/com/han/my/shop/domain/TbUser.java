package com.han.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.han.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import utils.RegexpUtils;

import javax.validation.constraints.Pattern;

/**
 * Created by han on 2019/9/3.
 */
@Data
public class TbUser extends BaseEntity {
    @Length(min=6,max = 20,message = "姓名的长度需介于6-20位之间")
    private String username;
    @JsonIgnore
    @Length(min=6,max = 20,message = "密码的长度需介于6-20位之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE,message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;
}
