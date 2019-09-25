package com.han.my.shop.web.admin.web.controller;



import com.han.my.shop.commons.constant.ConstantsUtils;
import com.han.my.shop.domain.TbUser;
import com.han.my.shop.domain.User;
import com.han.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by han on 2019/8/31.
 */
@Controller
public class LoginController {


    @Autowired
    private TbUserService tbUserService;

    /*
    * 跳转登录页面
    * */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login( HttpServletRequest httpServletRequest){
        String userInfo = CookieUtils.getCookieValue(httpServletRequest,ConstantsUtils.COOKIE_NAME_USERINFO );
        if (!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            httpServletRequest.setAttribute("email",email);
            httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("isRemember",true);
        }
        return "login";
    }

    /*
    * 登录逻辑
    * */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, String isRemember, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model){
        TbUser ebUser = tbUserService.login(email, password);
        boolean isRemembers = isRemember==null?false:true;
        //选择不记住
        if (!isRemembers){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,ConstantsUtils.COOKIE_NAME_USERINFO);
        }
        if (ebUser==null){
            //登录失败
            model.addAttribute("message","用户名或密码错误，请重新输入。");
            return "login";
        }else{
            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantsUtils.SESSION_USER,ebUser);
            //成功
            if (isRemembers){
                //用户信息存储一周
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,ConstantsUtils.COOKIE_NAME_USERINFO,String.format("%s:%s",email,password),7*24*60*60);
            }
            //登录成功
            return "redirect:/main";
        }
    }

    /*
    * 注销
    * */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout( HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return "login";
    }

}
