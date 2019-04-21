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
import java.util.*;

import static org.junit.Assert.*;

public class MonkeyDaoParamTest {
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
    public void getMonkeyById(){
        Monkey monkey = monkeyDao.getMonkeyById(1);
        System.out.println(JSONObject.toJSONString(monkey));
        // {"birthday":612939600000,"id":1,"name":"Jon Snow","phoneNumber":4794062}
    }

    // 如果数据库没有返回值，那么这里就是null
    @Test
    public void getMonkeyByNotExistId(){
        Monkey monkey = monkeyDao.getMonkeyById(9999999);
        Assert.assertNull(monkey);
    }

    @Test
    public void getMonkeyList(){
        List<Monkey> monkeys = monkeyDao.getMonkeyList(30);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [...]
    }

    @Test
    public void insertMonkey(){
        Monkey monkey = new Monkey();
        monkey.setName("Jon Snow");
        Boolean res = monkeyDao.insertMonkey(monkey);
        System.out.println(res);
        // true
    }

    @Test
    public void getMonkeysRangeById(){
        List<Monkey> monkeys = monkeyDao.getMonkeysRangeById(0,4);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [...]
    }

    @Test
    public void getMonkeysRangeByIdWithParamNames(){
        List<Monkey> monkeys = monkeyDao.getMonkeysRangeByIdWithParamNames(0,4);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [...]
    }

    // 通过Map来传递参数
    @Test
    public void getMonkeysRangeByIdWithMappedParamNames(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("min", 0);
        map.put("max", 3);
        List<Monkey> monkeys = monkeyDao.getMonkeysRangeByIdWithMappedParamNames(map);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [...]
    }

    // 如果期望返回一条数据，但是实际上返回了多条数据，就会抛出一下异常
    @Test(expected = TooManyResultsException.class)
    public void getOneMonkeyFromRangeById(){
        Map<String, Object> map = new HashMap<String, Object>();
        Monkey monkey = monkeyDao.getOneMonkeyFromRangeById(0, 4);
        System.out.println(JSONObject.toJSONString(monkey));
        // [...]
    }

    @Test
    public void getMonkeysByIds(){
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        List<Monkey> monkeys = monkeyDao.getMonkeysByIds(ids);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [...]
    }
}