package entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MonkeyTest {
    private static SqlSessionFactory factory;

    // 创建SqlSessionFactory
    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    // 查询一个记录
    @Test
    public void selectOneMonkey() {
        SqlSession session = factory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkey));
        // {"birthday":612939600000,"id":1,"name":"Jon Snow","phoneNumber":4794062}

        session.close();
    }

    // 查询一组记录
    @Test
    public void selectListMonkey(){
        SqlSession session = factory.openSession();

        List<Monkey> monkeys = session.selectList("selectMonkey", 1);
        System.out.println(JSONObject.toJSONString(monkeys));
        // [{"birthday":612939600000,"id":1,"name":"Jon Snow","phoneNumber":4794062}]

        session.close();
    }

    // 可以看到，查询一个记录和一组记录都是使用的 selectMonkey
    // 只是一个把查询结果映射为单个对象，一个把结果映射为列表

    @Test
    public void insertMonkey(){
        SqlSession session = factory.openSession();

        Monkey monkey = new Monkey();
        monkey.setName("Sun WuKong");
        int i = session.insert("insertMonkey", monkey);

        // 注意默认情况下，是需要显示的commit的
        session.commit();
        System.out.println(JSONObject.toJSONString(monkey));
        // 这时候就可以从数据库中查询到新增的记录的了

        session.close();
    }

    @Test
    public void insertMonkeyAutoCommit(){
        SqlSession session = factory.openSession(true);

        Monkey monkey = new Monkey();
        monkey.setName("Sun WuKong");
        int i = session.insert("entity.Monkey.insertMonkey", monkey);

        // 前面将autoCommit设置为true，这里就不用手动提交了
        // session.commit();

        session.close();
    }

    @Test(expected = org.apache.ibatis.exceptions.PersistenceException.class)
    public void insertMonkeyBug(){
        SqlSession session = factory.openSession();

        Monkey monkey = new Monkey();
        monkey.setName("Sun WuKong");

        // 应为mapper文件中有不止一个id为insertMonkey的SQL片段
        // 为了避免歧义，必须用 namespace + id 来引用
        int i = session.insert("insertMonkey", monkey);

        session.commit();
        session.close();
    }
}
