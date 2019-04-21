package dao;

import entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    public List<Article> getArticleWithCondition(Article article);

    public Integer updateArticlesWithCondition(Article article);

    public List<Article> getArticleListWithOrderAndPage(
            @Param("orderByField") String orderByField,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);
}
