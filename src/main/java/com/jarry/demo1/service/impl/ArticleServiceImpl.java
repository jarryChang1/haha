package com.jarry.demo1.service.impl;

import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.mapper.ArticleMapper;
import com.jarry.demo1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.service.impl
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-12 15:01
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article getOne(int id){
        return articleMapper.getOne(id);
    }
}
