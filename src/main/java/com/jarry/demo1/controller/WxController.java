package com.jarry.demo1.controller;


import com.jarry.demo1.Entry.Result;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private static final String strAppId = "wxeb7057d45e535444";
    private static final String strAppSecret = "18bf5d539166f99bee00940da93d6c0f";

    public WxController() {
        System.out.println("WxController构造函数");
    }

    //访问此资源的url:  http://localhost:8080/AutoLogin/wx/wxLogin.htm微信登录
    @RequestMapping(value = "/wxLogin.htm", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result wxreg(HttpServletRequest request, String code) {
        Result result = new Result();


        SnsToken token = SnsAPI.oauth2AccessToken(strAppId, strAppSecret, code);

        BaseResult baseResult = SnsAPI.auth(token.getAccess_token(), token.getOpenid());

        if ("0".equals(baseResult.getErrcode()) && "ok".equals(baseResult.getErrmsg())) {
            User user = SnsAPI.userinfo(token.getAccess_token(), token.getOpenid(), "zh-CN");
            System.out.println("user: " + user.toString());
            result.setStateCode("0");
            result.setDesc("成功获得信信登录用户");
            result.setData(user);
        } else {
            result.setStateCode("-1");
            result.setDesc("成功获得信信登录用户");
        }

        return result;
    }

    //前端请求getCode
    @GetMapping(value = "/getCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object wxcon() {
//        String reUrl = SnsAPI.connectQrconnect(strAppId,"http://z.haoma.cn","lalalalajarry");
        String reUrl = SnsAPI.connectOauth2Authorize(strAppId, "http://z.haoma.cn/pang/weixin/connectionServer", false, "lalalalajarry");
        System.out.println(reUrl);

        Object o = HttpUtils.sendRequest(reUrl, HttpMethod.POST, null, String.class);
        return o;
    }


    //测试函数
    public static void main(String[] args) {
//        String reUrl = SnsAPI.connectQrconnect(strAppId,"http://z.haoma.cn","lalalalajarry");
        String reUrl = SnsAPI.connectOauth2Authorize(strAppId, "http://z.haoma.cn/pang/weixin/connectionServer", false, "lalalalajarry");

        System.out.println(reUrl);
        Object o = HttpUtils.sendRequest(reUrl, HttpMethod.POST, null, String.class);
        System.out.println(o.toString());
        // TODO Auto-generated method stub
    }

}
