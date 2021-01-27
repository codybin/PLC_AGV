/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.mysql;

import com.mybatis.po.Employees;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author Lenovo
 */
public class MysqlService implements IMysqlOperation{
  private final SqlSessionFactory mSessionFactory;
  public MysqlService() {
    this.mysqlFactory =new MysqlFactory();
     mSessionFactory=mysqlFactory.getSqlSessionFactory();
  }
public   MysqlFactory mysqlFactory;

  @Override
  public Employees findOneByID(int id) {
  SqlSession sqlSession = null;
   Employees employees;
  try {
    // �� SqlSession �Ự
    sqlSession =mSessionFactory .openSession();
     employees = sqlSession.selectOne("com.mybatis.mapper.EmployeesMapper.getEmployee", id);//To change body of generated methods, choose Tools | Templates.
     sqlSession.commit();   
  } catch (Exception e) {
  System.out.println(e.getMessage());
 
    sqlSession.rollback(); 
     return null;// �ع�����
}finally{
    // �� finally �����ȷ����Դ��˳���ر�
    if(sqlSession != null){
        sqlSession.close();
    }
}
  return employees;
  }

  @Override
  public List<Employees> findAll() {
    SqlSession sqlSession = null;
   List<Employees> employees;
  try {
    // �� SqlSession �Ự
    sqlSession =mSessionFactory .openSession();
     employees = sqlSession.selectList("com.mybatis.mapper.EmployeesMapper.getAllEmployee");//To change body of generated methods, choose Tools | Templates.
    sqlSession.commit();   
  
  } catch (Exception e) {
     System.out.println(e.getMessage());
    sqlSession.rollback(); 
     return null;// �ع�����
}finally{
    // �� finally �����ȷ����Դ��˳���ر�
    if(sqlSession != null){
        sqlSession.close();
    }
}
  return employees; //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void Insert(Employees employees) {
    SqlSession sqlSession = null;
  try {
    // �� SqlSession �Ự
    sqlSession =mSessionFactory .openSession();
    if(findOneByID(employees.getId())==null)
        sqlSession.insert("com.mybatis.mapper.EmployeesMapper.addEmployee",employees);
     sqlSession.commit();   
  } catch (Exception e) {
  System.out.println(e.getMessage());
 
    sqlSession.rollback(); 
}finally{
    // �� finally �����ȷ����Դ��˳���ر�
    if(sqlSession != null){
        sqlSession.close();
    }
  }
  }

  @Override
  public void Delete(int id) {
       SqlSession sqlSession = null;

  try {
    // �� SqlSession �Ự
    sqlSession =mSessionFactory .openSession();
    if(findOneByID(id)!=null)
       sqlSession.delete("com.mybatis.mapper.EmployeesMapper.deleteEmployee", id);
     sqlSession.commit();   
  } catch (Exception e) {
  System.out.println(e.getMessage());
 
    sqlSession.rollback(); 
}finally{
    // �� finally �����ȷ����Դ��˳���ر�
    if(sqlSession != null){
        sqlSession.close();
    }
  } 
  }
  @Override
  public void Update(Employees employees) {
      SqlSession sqlSession = null;
  try {
    sqlSession =mSessionFactory .openSession();
    if(findOneByID(employees.getId())!=null)
    sqlSession.update("com.mybatis.mapper.EmployeesMapper.updateEmployee", employees); 
     sqlSession.commit();   
  } catch (Exception e) {
  System.out.println(e.getMessage());
    sqlSession.rollback(); 
}finally{
    // �� finally �����ȷ����Դ��˳���ر�
    if(sqlSession != null){
        sqlSession.close();
    }
  } 
  }
  
}
