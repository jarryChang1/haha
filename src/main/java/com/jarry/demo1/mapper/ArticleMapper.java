package com.jarry.demo1.mapper;

import com.jarry.demo1.Entry.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.mapper
 * @Author: Jarry.Chang
 * @CreateTime: 2019-11-13 14:31
 */
@Component
public interface ArticleMapper extends BaseMapper {
    //    @Select("SELECT * FROM article WHERE ID = #{id}")
    Article getOne(Integer id);
}
