/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.device;

import com.xintai.mysql.MysqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author Lenovo
 */
public class DestinationLocationService {
  private final MysqlFactory mysqlFactory;
  private final SqlSessionFactory mSessionFactory;

  public DestinationLocationService() {
       this.mysqlFactory =new MysqlFactory();
       mSessionFactory = mysqlFactory.getSqlSessionFactory();
  }
 public DestinationsLocations  findDestinationsMByOrderType(String  ordertype)
  {
   SqlSession sqlSession = null;
   DestinationsLocations  destinationsM=null;
   try {
    // 打开 SqlSession 会话
    sqlSession =mSessionFactory .openSession();
    destinationsM = sqlSession.selectOne("com.mybatis.mapper.LikuLocationMapper.getLikuLocation", ordertype);//To change body of generated methods, choose Tools | Templates.
    sqlSession.commit();   
  } catch (Exception e) {
  System.out.println(e.getMessage());
    sqlSession.rollback(); 
     return null;// 回滚事务
}finally{
    // 在 finally 语句中确保资源被顺利关闭
    if(sqlSession != null){
        sqlSession.close();
    }
}
   return destinationsM;
  }

}

