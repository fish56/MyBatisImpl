package com.github.fish56.mybatis.dao;

import com.github.fish56.mybatis.entity.Monkey;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

// 在配置文件中编写相关的Mapper后
// Mybatis能够自动的帮我们生成接口的实现类
public interface MonkeyDao {
    public Monkey getMonkeyById(Integer id);

    public Boolean insertMonkey(Monkey monkey);
    // 👇这样写也是可以的
    // public int insertMonkey(Monkey monkey);

    public List<Monkey> getMonkeyList(Integer limit);

    // 配置文件中可以通过#{param1} #{param2} 来接收到参数
    public List<Monkey> getMonkeysRangeById(Integer min, Integer max);

    public List<Monkey> getMonkeysRangeByIdWithParamNames
            (@Param("min") Integer min, @Param("max") Integer max);

    // 或者我们传递一个map类型，mapper文件中接口解构参数
    public List<Monkey> getMonkeysRangeByIdWithMappedParamNames
            (Map<String, Object> map);

    public Monkey getOneMonkeyFromRangeById
            (@Param("min") Integer min, @Param("max") Integer max);

    // 直接将数据库返回的结果通过column->field 映射成Map
    public Map<String, Object> getMonkeyMapById(Integer id);

    // 将数据库返回值映射成 主键->实体 的形式
    // 需要通过MapKey注解指明主键
    @MapKey("id")
    public Map<Integer, Monkey> getMonkeyKeyMapById(Integer id);

    // 通过列表传递参数
    public List<Monkey> getMonkeysByIds(List<Integer> ids);
}
