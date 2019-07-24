package com.github.fish.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = Statement.class)
})
public class MyPlugin implements Interceptor {
    public Object intercept(Invocation invocation) throws Throwable {
        Object obj = invocation.proceed();
        System.out.println("public Object intercept(Invocation invocation) throws Throwable {");
        return obj;
    }

    public Object plugin(Object target) {
        // 借助自带的包装方法创建包装类
        System.out.println(" public Object plugin(Object target)");
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
