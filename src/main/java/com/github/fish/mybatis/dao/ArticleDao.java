package com.github.fish.mybatis.dao;

import com.github.fish.mybatis.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    List<Article> getArticleWithCondition(Article article);

    /**
     * 将结果映射成Map
     * @param id
     * @return
     */
    Map<String, String> selectArticleInfo(Integer id);

    /**
     * 通过ID来查询
     * @param ids
     * @return
     */
    List<Article> selectArticlesByIds(List<Integer> ids);

    Integer updateArticlesWithCondition(Article article);

    List<Article> getArticleListWithOrderAndPage(
            @Param("orderByField") String orderByField,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);
}
