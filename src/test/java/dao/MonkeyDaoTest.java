package dao;

import com.alibaba.fastjson.JSONObject;
import entity.Monkey;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MonkeyDaoTest {
    private static SqlSessionFactory factory;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void getMonkeyById(){
        SqlSession session = factory.openSession();

        // get implementation of MonkeyDao from MyBatis
        // wow, it is amazing!!!
        MonkeyDao monkeyDao = session.getMapper(MonkeyDao.class);
        Monkey monkey = monkeyDao.getMonkeyById(1);
        System.out.println(JSONObject.toJSONString(monkey));
    }
}