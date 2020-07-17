package com.learn.hibernate;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Employee;

public class MainApp2 {
   public static void main(String[] args) {
	   StatelessSession session = null;
	   Transaction transaction = null;
	   try {
		   session = HibernateUtil.getSessionFactory().openStatelessSession();
//		   transaction = session.beginTransaction();
//		   Employee e = (Employee) session.get(Employee.class, 1L);
//		   System.out.println(e.getName());
//		   
		   Query<Employee> query = session.createQuery("select e from Employee e");
		   List<Employee> rows = query.getResultList();
		   for(Employee row : rows){
		   	Employee emp = row;
		   	System.out.println(emp);
		   }
		   
		   transaction.commit();
		   
	   } catch (Exception e) {
		   System.err.println("Error!! Executing Roll Back"+e.toString());
		   transaction.rollback();
	   }
	   finally {
		   session.close();
	   }
	   
	   
   }
}
