package sql;

import com.alibaba.fastjson.JSONObject;
import entity.Article;
import entity.Monkey;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CrudTest {
    private static SqlSessionFactory factory;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void selectOneArticle() {
        SqlSession session = factory.openSession();

        Article article = session.selectOne("selectArticle", 2);
        System.out.println(JSONObject.toJSONString(article));

        session.close();
    }
}
