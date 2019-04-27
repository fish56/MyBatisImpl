package base;

import com.alibaba.fastjson.JSONObject;
import dao.ArticleDao;
import dao.MonkeyDao;
import entity.Monkey;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FactoryTest {
    protected MonkeyDao monkeyDao;
    protected ArticleDao articleDao;

    @Before
    public void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // MyBatis能够根据配置文件自动的帮我们生成MonkeyDao的实现类
        monkeyDao = session.getMapper(MonkeyDao.class);

        // MyBatis能够根据配置文件自动的帮我们生成ArticleDao的实现类
        articleDao = session.getMapper(ArticleDao.class);
    }

    @Test
    public void isEnvOk(){
    }
}