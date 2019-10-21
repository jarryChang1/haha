package com.jarry.demo1.controller;


import com.jarry.demo1.Entry.Result;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jarry.Chang
 * @CreateTime: 2019-10-09 17:52
 */
@Controller
@RequestMapping("/wx")
public class WxController {


    public WxController() {
        System.out.println("WxController构造函数");
    }

    //访问此资源的url:  http://localhost:8080/AutoLogin/wx/wxLogin.htm微信登录
    @RequestMapping(value = "/wxLogin.htm", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result wxreg(HttpServletRequest request, String code){
        Result result = new Result();

        String strAppId ="";
        String strAppSecret = "";
        SnsToken token = SnsAPI.oauth2AccessToken(strAppId, strAppSecret, code);

        BaseResult baseResult = SnsAPI.auth(token.getAccess_token(), token.getOpenid());

        if("0".equals(baseResult.getErrcode()) && "ok".equals(baseResult.getErrmsg())){
            User user = SnsAPI.userinfo(token.getAccess_token(), token.getOpenid(), "zh-CN");
            System.out.println("user: " + user.toString());
            result.setStateCode("0");
            result.setDesc("成功获得信信登录用户");
            result.setData(user);
        }else{
            result.setStateCode("-1");
            result.setDesc("成功获得信信登录用户");
        }

        return result;
    }


    //测试函数
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
    }

}
