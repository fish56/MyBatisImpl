package dao;

import entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    public List<Article> getArticleWithCondition(Article article);

    // 将结果映射成Map
    public Map<String, String> selectArticleInfo(Integer id);

    public Integer updateArticlesWithCondition(Article article);

    public List<Article> getArticleListWithOrderAndPage(
            @Param("orderByField") String orderByField,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset);
}
