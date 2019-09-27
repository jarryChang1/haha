package com.jarry.demo1.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jarry.demo1.Entry.UserBean;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @CreateTime: 2019-09-17 17:38
 */
@RestController
@RequestMapping(value = "base")
public class BaseController {
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
}
