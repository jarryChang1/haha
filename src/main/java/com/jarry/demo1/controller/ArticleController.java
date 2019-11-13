package com.jarry.demo1.controller;

import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.Entry.Result;
import com.jarry.demo1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getArticle/{id}")
    public Result getArticle(@PathVariable("id") int id){
        Result s = new Result();
        Article article = articleService.getOne(id);
        s.setData(article);
        return s;
    }
}
