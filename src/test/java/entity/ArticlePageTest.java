package entity;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArticlePageTest extends FactoryTest{
    // 查询一组记录
    @Test
    public void selectList(){
        SqlSession session = factory.openSession();
        // 开启分页
        PageHelper.startPage(2, 2);
        List<Article> articles = session.selectList("selectArticles");
        System.out.println(JSONObject.toJSONString(articles));

        // 查询结果是分页的了
        // "content":"dd","id":2,"title":"a title"}]

        session.close();
    }
}
