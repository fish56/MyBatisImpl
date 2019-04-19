package entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MonkeyBugTest {
    private static SqlSessionFactory factory;

    // 创建SqlSessionFactory
    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test(expected = org.apache.ibatis.exceptions.PersistenceException.class)
    public void insertMonkeyBug(){
        SqlSession session = factory.openSession();

        Monkey monkey = new Monkey();
        monkey.setName("Sun WuKong");

        // 因为mapper文件中有不止一个id为insertMonkey的SQL片段
        // 为了避免歧义，必须用 namespace + id 来引用
        int i = session.insert("insertMonkey", monkey);

        session.commit();
        Assert.assertEquals(1, i);
        session.close();
    }
}
