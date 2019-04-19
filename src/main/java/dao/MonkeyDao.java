package dao;

import entity.Monkey;

// 在配置文件中编写相关的Mapper后
// Mybatis能够自动的帮我们生成接口的实现类
public interface MonkeyDao {
    public Monkey getMonkeyById(Integer id);
    public Boolean insertMonkey(Monkey monkey);
}
