package com.github.fish56.mybatis.mybatis.dao;

import com.github.fish56.mybatis.mybatis.FactoryTest;
import com.alibaba.fastjson.JSONObject;
import com.github.fish56.mybatis.entity.Monkey;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MonkeyDaoParamTest extends FactoryTest {

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
        assertTrue(res);
        // true
    }

    @Test
    public void insertExistMonkey(){
        // id为2的猴子已经存在了
        //   再次插入应该抛出错误
        Monkey monkey = new Monkey();
        monkey.setId(2);
        monkey.setName("Jon Snow");
        Boolean res = monkeyDao.insertMonkey(monkey);

        // 不过我在mapper文件中做了保险，
        //   丢弃了id字段，所以这里没有问题
        System.out.println(res);
        // assertFalse(res);
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