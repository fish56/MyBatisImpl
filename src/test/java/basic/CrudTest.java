package basic;

import com.alibaba.fastjson.JSONObject;
import entity.Monkey;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CrudTest {
    @Test
    public void selectMonkey() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory
                = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = sessionFactory.openSession();

        Monkey monkey = session.selectOne("selectMonkey", 1);

        System.out.println(JSONObject.toJSONString(monkey));

        session.close();
    }
}
