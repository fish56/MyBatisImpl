package dao;

import base.FactoryTest;
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

public class MonkeyDaoResultMapTest extends FactoryTest {

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