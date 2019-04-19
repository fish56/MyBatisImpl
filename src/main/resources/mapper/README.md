MyBatis最重要的莫过于
- 参数映射
- 结果映射
- 动态SQL语句了

## 参数映射
如果参数是单个普通的Java类型，在配置文件中直接使用它
Monkey getMonkey(Integer id)
  取出id ==> #{id}

如果参数是Java POJO，那么配置文件中直接使用这个对象的属性
Boolean insertMonkey(Monkey monkey)
  取出属性
    id ==> #{id}
    name ==> #{name}

如果参数是多个普通类型，那么它们或被包装成Map
Boolean getMonkey(Integer id, String name)
  取出id ==> #{param1}
  取出name ==> #{param2} 

Monkey getMonkey(Integer id,@Param("m") Monkey monkey)
  取出id ==> #{param1} 
  取出monkey.name ==> #{m.name/param2.name} 

Monkey getMonkey(List<Integer> ids)
  取出第一个id #{list[0]}
  
## 结果映射
resultType="map"
直接把数据库的结果按照column名 -> row值映射成一个map

