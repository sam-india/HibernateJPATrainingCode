package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Employee;

public class FetchDataClientMainApp {
   public static void main(String[] args) {
	   Employee employee = null;
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
         employee = session.load(Employee.class, 1);
         System.out.println(employee.toString());
         System.out.println(employee.getAddress().getCity());
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      HibernateUtil.shutdown();
   }
}
