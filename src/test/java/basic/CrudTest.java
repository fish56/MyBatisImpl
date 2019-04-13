package basic;

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
import java.util.List;

public class CrudTest {
    private static SqlSessionFactory factory;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void selectOneMonkey() {
        SqlSession session = factory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));

        session.close();
    }

    @Test
    public void selectListMonkey(){
        SqlSession session = factory.openSession();

        List<Monkey> monkeys = session.selectList("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkeys));

        session.close();
    }

    @Test
    public void insertMonkey(){
        SqlSession session = factory.openSession();

        Monkey monkey = new Monkey();
        monkey.setName("Sun WuKong");
        int i = session.insert("insertMonkey", monkey);

        session.commit();
        System.out.println(JSONObject.toJSONString(monkey));
        // {"id":6,"name":"Sun WuKong"}
        session.close();
    }

    @Test
    public void selectListMonkey2(){
    }
}
