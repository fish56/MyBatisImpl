package entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ArticleTest {
    private static SqlSessionFactory factory;

    // 创建SqlSessionFactory
    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    // 查询一个记录
    @Test
    public void selectOneArticle() {
        SqlSession session = factory.openSession();

        Article article = session.selectOne("selectArticle", 2);
        System.out.println(JSONObject.toJSONString(article));
        // {"author":{"id":1,"name":"Jon Snow"},
        // "content":"dd","id":2,"title":"a title"}

        session.close();
    }

    // 查询一组记录
    @Test
    public void selectListArticle(){
        SqlSession session = factory.openSession();

        List<Article> articles = session.selectList("selectArticle", 2);
        System.out.println(JSONObject.toJSONString(articles));
        // [{"author":{"id":1,"name":"Jon Snow"},
        // "content":"dd","id":2,"title":"a title"}]

        session.close();
    }

    // 可以看到，查询一个记录和一组记录都是使用的 selectMonkey
    // 只是一个把查询结果映射为单个对象，一个把结果映射为列表
}
