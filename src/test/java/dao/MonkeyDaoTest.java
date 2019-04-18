package dao;

import com.alibaba.fastjson.JSONObject;
import entity.Monkey;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MonkeyDaoTest {
    private static MonkeyDao monkeyDao;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // get implementation of MonkeyDao from MyBatis
        // wow, it is amazing!!!
        monkeyDao = session.getMapper(MonkeyDao.class);
    }

    @Test
    public void getMonkeyById(){
        Monkey monkey = monkeyDao.getMonkeyById(1);
        System.out.println(JSONObject.toJSONString(monkey));
    }
    @Test
    public void insertMonkey(){
        Monkey monkey = new Monkey();
        monkey.setName("Jon Snow");
        Boolean res = monkeyDao.insertMonkey(monkey);
        System.out.println(res);
        // true
    }
}