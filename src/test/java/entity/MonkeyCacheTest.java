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

public class MonkeyCacheTest {
    private static SqlSessionFactory factory;

    // 创建SqlSessionFactory
    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    // 一级缓存是默认开启的，无法关闭
    //   在同一个session中有效
    //   需要查询条件一致才会缓存
    @Test
    public void cache() {
        SqlSession session = factory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));
        // {"birthday":612939600000,"id":1,"name":"Jon Snow","phoneNumber":4794062}

        Monkey monkey2 = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));

        Assert.assertSame(monkey, monkey2);

        // 第一次查询后，数据被缓存下来了
        // 第二次查询并没有向数据库中发起请求，
        //   而是直接把缓存中的对象返回了

        session.close();
    }

    @Test
    public void unCache() {
        SqlSession session = factory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));

        Monkey newMonkey = new Monkey();
        newMonkey.setName("Sun WuKong");
        session.insert("entity.Monkey.insertMonkey", newMonkey);
        // 增删改会导致缓存失效

        Monkey monkey2 = session.selectOne("selectMonkey", 1);

        Assert.assertNotSame(monkey, monkey2);

        session.close();
    }

    @Test
    public void clearCache() {
        SqlSession session = factory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));

        // 手动删除缓存
        session.clearCache();

        Monkey monkey2 = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));

        Assert.assertNotSame(monkey, monkey2);

        session.close();
    }

    @Test
    public void cacheLevel2() {
        // todo
    }
}
