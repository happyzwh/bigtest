
单进程多数据源事务  
  
特性:  
1、jdbc ORM 实现 简单 增、删、改、查、列表、数量查询  
2、c3p0封装数据源及jdbc封装而成的session及事务操作   
3、注解事务利用aop对事务操作   
4、基本的泛型DAO 增删除改查列表 关系对象映射   
5、jta跨库事务控制  
  
使用方法：  
1、dataSource.xml 中配置数据源  
2、参考 com.zwh.domain.Test 增加注解 实现 与库表及字段映射  
3、参考 com.zwh.dao.TestDaoImpl 实现DAO操作  
4、在service事务方法上 增加 @JtaTransactionAnno 注解  
  
测试:  
1、创建数据库  
   执行test.sql  
2、运行 com.zwh.test.JtaTest main 方法   
  
架构：  
1、实体类上 注解 数据源名称、表名、字段名 ，运行时 通过反射 进行 ORM 实现 对象 关系表映射，自动生成jdbc sql语句   
2、封装 SessionProxyImpl 实现对 一次事务 不同数据源 多次会话 操作的控制，实现 全部提交或回滚  
3、封装 JtaTransactionImpl 控制事务的边界  
4、封装 BasicJtaDataSourceFactory 获取不同数据源的连接  
  
类图：  
1、bigtest.png     
