package dao;

import com.alibaba.fastjson.JSONObject;
import entity.Monkey;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonkeyDaoResultMapTest {
    private static MonkeyDao monkeyDao;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // MyBatis能够根据配置文件自动的帮我们生成MonkeyDao的实现类
        monkeyDao = session.getMapper(MonkeyDao.class);
    }

    @Test
    public void getMonkeyMapById(){
        Map<String, Object> monkey = monkeyDao.getMonkeyMapById(1);
        System.out.println(JSONObject.toJSONString(monkey));
        // {"birthday":612939600000,"name":"Jon Snow","phone_number":4794062,"id":1}
    }

    @Test
    public void getMonkeyKeyMapById(){
        Map<Integer, Monkey> monkey = monkeyDao.getMonkeyKeyMapById(1);
        System.out.println(JSONObject.toJSONString(monkey));
        // {1:{"birthday":612939600000,"name":"Jon Snow","phone_number":4794062,"id":1}}
    }
}