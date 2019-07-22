package com.github.fish.mybatis.dao;

import com.github.fish.mybatis.FactoryTest;
import com.alibaba.fastjson.JSONObject;
import com.github.fish.mybatis.entity.Monkey;
import org.junit.Test;

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