package com.github.fish56.mybatis.dao;

import com.github.fish56.mybatis.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    public List<Article> getArticleWithCondition(Article article);

    // 将结果映射成Map
    public Map<String, String> selectArticleInfo(Integer id);

    // 通过ID来查询
    public List<Article> selectArticlesByIds(List<Integer> ids);

    public Integer updateArticlesWithCondition(Article article);

    public List<Article> getArticleListWithOrderAndPage(
            @Param("orderByField") String orderByField,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);
}