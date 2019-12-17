package com.zwh.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwh.dao.TestDao;
import com.zwh.exception.BasicException;
public class MainTest {
    
	public static void main(String[] args) throws BasicException, ClassNotFoundException, InstantiationException, IllegalAccessException{
//		String sql="insert into ttt(name,score) values('r',10);";
//		String Sql="update ttt set name = 'qq' , score = 22 where id=2;";
//		BasicSessionFactory.getInstance().getSession().saveAndUpdate(sql, Sql);
		
//		new tttDaoImpl().delete("delete from ttt where id = 2;");
		
//		List<Map<String,Object>> list = new TttDaoImpl().quertyList("select * from ttt");
//		if(list != null && list.size() >0){
//			for(Map<String,Object> map:list){
//				System.out.println((String)map.get("name")+" "+(Integer)map.get("score"));
//			}
//		}
//		TttService tttService = new TttServiceImpl();
//		List<Map<String,Object>> list = tttService.quertyList("select * from ttt");
//		if(list != null && list.size() >0){
//			for(Map<String,Object> map:list){
//				System.out.println((String)map.get("name")+" "+(Integer)map.get("score"));
//			}
//		}
//		try{
//			TttService tttService = new TttServiceImpl();
//			BasicSessionFactory.getInstance().getSession().begin();
//			tttService.save("insert into ttt(name,score) values('rr',100)");
////			int i = 0;
////			i = 10/i;
//			tttService.update("update ttt set name = '22' , score = 200 where id = 3");
//			BasicSessionFactory.getInstance().getSession().commit();
//		}catch(Exception e){
//			BasicSessionFactory.getInstance().getSession().rollback();
//			e.printStackTrace();
//		}finally{
//			BasicSessionFactory.getInstance().getSession().close();
//		}

		 ClassPathXmlApplicationContext beanApp = new ClassPathXmlApplicationContext("applicationContext.xml");  
		 TestDao dao =  (TestDao)beanApp.getBean("testDao");  
		 
//		 Test test = new Test();
//		 test.setName("name19");
//		 test.setDes("des19");
//		 test.setAge(19);
//		 test.setCreatetime(new Date());
//		 System.out.println(dao.save(test));
		 
		 
//		 Test test = new Test();
//		 test.setId(17);
//		 test.setName("name171");
//		 test.setDes("des171");
//		 test.setAge(171);
//		 test.setCreatetime(new Date());
//		 System.out.println(dao.update(test));
		 
//		 System.out.println(dao.delete(17));
		 
//		 Test test = new Test();
//		 test.setId(16);
//		 System.out.println(dao.delete(test));
		 
//		 Test test = new Test();
//		 test.setName("n15ame");
//		 test.setDes("des15");
//		 test = dao.get(15);
//		 test = dao.get(test);
//		 System.out.println(test.getId()+" "+test.getDes()+" "+test.getName()+" "+test.getAge()+" "+test.getCreatetime());
		
//		 String sqlStr="select * from test";
//		 Object[] param = new Object[]{};
//		 List<Test> list =  dao.list(sqlStr, param);
//		 if(list != null && list.size() > 0){
//		   for(Test test:list)
//			 System.out.println(test.getId()+" "+test.getDes()+" "+test.getName()+" "+test.getAge()+" "+test.getCreatetime());
//		 }
		 
		 String sqlStr="select * from test";
		 Object[] param = new Object[]{};
		 int count =  dao.count(sqlStr, param);
         System.out.println(count);
	}
}
