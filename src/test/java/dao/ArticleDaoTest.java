package dao;

import base.FactoryTest;
import com.alibaba.fastjson.JSONObject;
import entity.Article;
import entity.Monkey;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDaoTest extends FactoryTest {
    @Test
    public void getArticleWithCondition(){
        Article articleTemp = new Article();
        articleTemp.setTitle("a title");
        List<Article> articles = articleDao.getArticleWithCondition(articleTemp);
        System.out.println(JSONObject.toJSONString(articles));
        // [...]

        // 实际的SQL语句是这样的 select * from article WHERE title = ?=
        // 可以看到，因为content为空，所以MyBatis的动态SQL就没有携带SQL分支语句
    }

    @Test
    public void getArticleWithCondition2(){
        Article articleTemp = new Article();
        articleTemp.setTitle("a title");
        articleTemp.setContent("content");
        List<Article> articles = articleDao.getArticleWithCondition(articleTemp);
        System.out.println(JSONObject.toJSONString(articles));
        // [...]

        // 实际的SQL语句是这样的 select * from article WHERE title = ? and content = ?
    }

    @Test
    public void updateArticlesWithCondition(){
        Article articleTemp = new Article();
        articleTemp.setTitle("a title");
        articleTemp.setContent("content");
        articleTemp.setId(1);
        Integer res = articleDao.updateArticlesWithCondition(articleTemp);
        System.out.println(res);
        // [...]

        // 实际的SQL语句是这样的 select * from article WHERE title = ? and content = ?
    }

    @Test
    public void getArticleListWithOrderAndPage(){
        List<Article> articles = articleDao
                .getArticleListWithOrderAndPage("id", 2,1);
        System.out.println(JSONObject.toJSONString(articles));

    }

    @Test
    public void selectArticleInfo(){
        Map<String, String> map = articleDao.selectArticleInfo(1);
        System.out.println(JSONObject.toJSONString(map));
        // {"articleCountByAuthor":3}
    }

    @Test
    public void selectArticlesByIds(){
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<Article> articles = articleDao.selectArticlesByIds(ids);
        System.out.println(JSONObject.toJSONString(articles));
        Assert.assertTrue(articles.size() > 0);
    }
}