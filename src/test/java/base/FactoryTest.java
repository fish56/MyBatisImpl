package base;

import dao.ArticleDao;
import dao.MonkeyDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

// 创建一个基础测试类，初始化环境
//   其他测试类可以直接继承这个父类来获得环境配置
public class FactoryTest {
    protected MonkeyDao monkeyDao;
    protected ArticleDao articleDao;

    @Before
    public void init() throws IOException {
        // 通过配置文件创建sessionFactory
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        // 开启一个session
        SqlSession session = factory.openSession();

        // MyBatis能够根据配置文件自动的帮我们生成dao层接口的实现类
        //    接口的类名必须和mapper文件的命名空间保持一致
        //    接口的方法名必须和mapper文件中的sqlID保持一致
        monkeyDao = session.getMapper(MonkeyDao.class);
        articleDao = session.getMapper(ArticleDao.class);
    }

    @Test
    public void isEnvOk(){}
}