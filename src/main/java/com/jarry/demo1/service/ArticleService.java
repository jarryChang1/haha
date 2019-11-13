package com.jarry.demo1.service;

import com.jarry.demo1.Entry.Article;
import com.jarry.demo1.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.service
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-12 15:00
 */
public interface ArticleService {

    public Article getOne(int id);

}
