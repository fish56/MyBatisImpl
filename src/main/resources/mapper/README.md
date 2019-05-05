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
- Monkey getMonkey(Integer id)
  - 取出id ==> #{id}

如果参数是Java POJO，那么配置文件中直接使用这个对象的属性
- Boolean insertMonkey(Monkey monkey)
  - 取出id ==> #{id}
  - 取出name ==> #{name}

如果参数是多个普通类型，那么它们或被包装成Map
- Boolean getMonkey(Integer id, String name)
  - 取出id ==> #{param1}
  - 取出name ==> #{param2} 
  
- Monkey getMonkey(Integer id,@Param("m") Monkey monkey)
  - 取出id ==> #{param1} 
  - 取出monkey.name ==> #{m.name/param2.name} 

参数是列表
- Monkey getMonkey(List<Integer> ids)
  - 取出第一个id #{list[0]}
  
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

### foreach
在使用foreach的时候最容易出错的就是collection属性，该属性是必须指定的，在不同情况 下，该属性的值是不一样的，主要有一下3种情况：

1.  如果传入的是单参数且参数类型是一个List的时候，collection属性值为list

2.  如果传入的是单参数且参数类型是一个array数组的时候，collection的属性值为array

3.  如果传入的参数是多个的时候，我们就需要把它们封装成一个Map了，当然单参数也可以封装成map，实际上如果你在传入参数的时候，在breast里面也是会把它封装成一个Map的，map的key就是参数名，所以这个时候collection属性值就是传入的List或array对象在自己封装的map里面的key.

