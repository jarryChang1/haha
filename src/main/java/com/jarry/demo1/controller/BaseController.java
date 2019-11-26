package com.jarry.demo1.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.Entry.Result;
import com.jarry.demo1.Entry.UserBean;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
import com.jarry.demo1.mapper.ArticleMapper;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @CreateTime: 2019-09-17 17:38
 */
@Controller
@RequestMapping(value = "base")
public class BaseController {
    @Resource
    ArticleMapper articleMapper;
    @PostMapping("saveOrUpdate")
    public Integer lalala(String s){
//        UserBean userBean = new Gson().fromJson(s,UserBean.class);
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(s).getAsJsonArray();
        List<UserBean> list = new ArrayList<>();
        Gson gson = new Gson();
        jsonArray.forEach(g -> list.add(gson.fromJson(g,UserBean.class)));

        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        JsonArray jsonArray1 = jsonObject.getAsJsonArray();
        jsonArray1.forEach(g -> list.add(gson.fromJson(g,UserBean.class)));
        list.stream().filter(g -> (g.getAge())>30);
        String json = "{\"name\":\"fly\",\"age\":25}";
        Map<String ,String> map = new HashMap();
        if(map.entrySet().iterator().hasNext()){
            Map.Entry<String,String> entry = map.entrySet().iterator().next();
            String g1 =entry.getKey().toString()+entry.getValue().toString();
        }
        for (Map.Entry<String,String> entry : map.entrySet()
             ) {
            entry.getKey();
            entry.getValue();
        }
//        Comparator<Integer> comparator = Comparator.comparingInt(UserBean ::getPhone);
//        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        return 1;

    }

    @GetMapping("getOne")
    public Result getOne(Integer id){
        Result<Article> result = new Result<>();
        result.setData(articleMapper.getOne(id));
        return result;
    }
    @GetMapping("index")
    public String index(){
        return "/index";
    }

}
