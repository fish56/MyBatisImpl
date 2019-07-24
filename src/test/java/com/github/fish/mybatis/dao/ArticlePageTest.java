package com.github.fish.mybatis.dao;

import com.alibaba.fastjson.JSONObject;
import com.github.fish.mybatis.entity.Article;
import com.github.fish.mybatis.FactoryTest;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;

import java.util.List;

public class ArticlePageTest extends FactoryTest{
    /**
     * 不使用分页
     */
    @Test
    public void selectList(){
        List<Article> articles = articleDao.getArticleWithCondition(null);
        System.out.println(JSONObject.toJSONString(articles));
    }

    /**
     * 使用分页
     */
    @Test
    public void page(){
        Page page = PageHelper.startPage(2, 2);
        List<Article> articles = articleDao.getArticleWithCondition(null);
        System.out.println(JSONObject.toJSONString(articles));
        // [{"content":"content","id":3,"title":"a title3"},{"content":"content","id":4,"title":"a title4"}]
        // 确实做到了分页查询
    }
}
