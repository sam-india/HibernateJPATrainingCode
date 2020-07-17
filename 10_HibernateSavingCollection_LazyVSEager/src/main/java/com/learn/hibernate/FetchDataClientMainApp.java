package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Employee;

public class FetchDataClientMainApp {
   public static void main(String[] args) {
	   Employee employee = null;
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
         employee = session.get(Employee.class, 1);
         System.out.println(employee.toString());
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      if(employee != null)
      {
     	 employee.getAddressList().stream().forEach(add -> System.out.println(add));
      }
      
      HibernateUtil.shutdown();
   }
}
