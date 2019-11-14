package com.jarry.demo1.controller;

import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.Entry.Result;
import com.jarry.demo1.service.ArticleService;
import com.jarry.demo1.utils.ExcelUtils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

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
}
