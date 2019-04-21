MyBatis最重要的莫过于
- 参数映射
  - 参数只有一个
    - 参数是基本类型
    - 是POJO
    - 是map
    - 是list
  - 参数是多个
- 结果映射
  - 结果只有一行
    - 只有一个字段
      映射成什么样的基本类型
    - 有多个字段
      每个字段映射成对象的哪个属性
  - 结果有多行
    没一行如何映射
- 动态SQL语句了
  - if
  - where
  - set

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

## SQL
insert update delete 比较简单，没什么好说的

就是select
- from
- where
- orderBy
- offset
- limit

