package com.learn.hibernate;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Employee;

public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
	      	Employee employee1 = new Employee("Scott", "Scott@js.com", new Date(), 50000.00);
	     	Address address1 = new Address("Park street", "Pune", "Maharashtra", 411057L);
	        Address address2 = new Address("Park street 1", "Pune 1", "Maharashtra 1", 411057L);
	        
	        employee1.getAddresses().add(address1);
	        employee1.getAddresses().add(address2);
        session.save(employee1);
        
       	transaction.commit();
         System.out.println("Employee Data saved successfully");
         
      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}
