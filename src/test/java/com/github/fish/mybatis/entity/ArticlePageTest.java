package com.github.fish.mybatis.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ArticlePageTest extends FactoryTest{
    /**
     * 不使用分页
     */
    @Test
    public void selectList(){
        SqlSession session = factory.openSession();

        List<Article> articles = session.selectList("selectArticles");
        System.out.println(JSONObject.toJSONString(articles));

        session.close();
    }

    /**
     * 使用分页，这里直接传递一个RowBounds就行了
     */
    @Test
    public void page(){
        SqlSession session = factory.openSession();

        List<Article> articles = session.selectList("selectArticles", null, new RowBounds(2,2));
        System.out.println(JSONObject.toJSONString(articles));

        session.close();
    }
}
