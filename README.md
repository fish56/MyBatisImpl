# MyBatis Implementation

通过代码示例 + 丰富的注释囊括MyBatis主要的知识点。
每个测试用例都是一个知识点！

虽然看起来复杂，项目内容很多，但其实它们之间独立性很好，可以选择性的阅读。

建议clone本地后，用IDEA之类的IDE打开阅读。

## 程序运行的基本流程
### 基于session的
1. 加载配置文件
2. 通过配置文件创建sesionFactory
3. 通过sessionFactory获得session，每个session代表一个数据库连接
4. 每一个mapper文件对应一个数据库操作
5. 通过session执行mapper中的文件

### 将mapper映射为interface的
每个interface必须和mapper文件保持一致
- 接口类名和mapper文件的命名空间一致
- 接口方法名和mapper文件的id保持一致

这个也是Java风格，将一些数据库连接、REST 请求和借口绑定在一起。

## 核心
参数配置 ==》 动态SQL ==》 返回值映射



## 推荐资料
- 官方文档
  - http://www.mybatis.org/mybatis-3/zh/index.html

- 尚硅谷免费视频教程：
  - https://www.bilibili.com/video/av34875242
  - https://www.bilibili.com/video/av40809206
  

