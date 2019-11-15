package com.jarry.demo1.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.OneRowAnalysisFinishEvent;
import com.alibaba.excel.metadata.Sheet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.Entry.Result;
import com.jarry.demo1.service.ArticleService;
import com.jarry.demo1.utils.ExcelUtils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.controller
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-12 15:53
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    //根据id获取文章内容
    @GetMapping("getArticle/{id}")
    public Result getArticle(@PathVariable("id") int id){
        Result s = new Result();
        Article article = articleService.getOne(id);
        s.setData(article);
        return s;
    }

    //导入文章的excel，处理数据; 没有定制逻辑，只能自己用
    @PostMapping("importExcel")
    public Result importExcel(@RequestParam("file")MultipartFile file){
        List<Object> list = ExcelUtils.importExcel(file,Article.class);
        if(list != null) {
            System.out.println(list.toString());
            return new Result("0","成功",list);
        }
        return null;
    }

    @PostMapping("importExcel2")
    public Result importExcel2(@RequestParam("file")MultipartFile file) throws IOException {
        List<Object> list = new ArrayList<>();
        Gson gson = new Gson();
        Map<String,String> fieldExplain = new LinkedHashMap<>();
        fieldExplain.put("title","标题");
        fieldExplain.put("abstracts","摘要");
        fieldExplain.put("content","内容");
        Map<String,Object> field = new LinkedHashMap<>(16);
        Map<String,Integer> headIndex = new LinkedHashMap<>(16);
        AnalysisEventListener excelListener = new AnalysisEventListener() {
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                //当前读到的excel值
                List<String> excelList = gson.fromJson(gson.toJson(o), new TypeToken<List<String>>() {
                }.getType());
                System.out.println("当前行:" + analysisContext.getCurrentRowNum());
                System.out.println(excelList);
                if(analysisContext.getCurrentRowNum() == 0){
                    for (int i = 0;i< excelList.size();i++) {
                        for (Map.Entry<String, String> entry : fieldExplain.entrySet()
                                ) {
                              if (entry.getValue().equals(excelList.get(i))){
                                  field.put(entry.getKey(),entry.getValue());
                                  headIndex.put(entry.getKey(),i);
                              }
                        }
                    }
                }else {
                    for (Map.Entry<String,Object> entry: field.entrySet()
                         ) {
                        for (Map.Entry<String,Integer> headEntry:headIndex.entrySet()
                             ) {
                            if(entry.getKey().equals(headEntry.getKey())){
                                field.replace(entry.getKey(),excelList.get(headEntry.getValue()));
                            }
                        }
                    }
                Article article = gson.fromJson(gson.toJson(field),new TypeToken<Article>(){}.getType());

//                list.add(o);
                doSomething(article);
                }
            }
            public void doSomething(Article o){
                //入库等操作，方法可设计
                System.out.println(o.toString());
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            list.clear();
            }
        };
        EasyExcelFactory.readBySax(file.getInputStream(),new Sheet(1,0),excelListener);

        if(list != null) {
            System.out.println(list.toString());
            return new Result("0","成功",list);
        }
        return null;
    }
}
