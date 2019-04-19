package dao;

import entity.Article;

import java.util.List;

public interface ArticleDao {
    public List<Article> getArticleWithCondition(Article article);
}
