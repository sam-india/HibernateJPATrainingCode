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
     	Employee employee2 = new Employee("Jeremy", "jeremy@js.com", new Date(), 25000.00);
     	Address address1 = new Address("Park street", "Pune", "Maharashtra", 411057L);
        Address address2 = new Address("Park street 1", "Pune 1", "Maharashtra 1", 411057L);
        employee1.setAddress(address1);
        employee2.setAddress(address2);
       
//In case of No Cascading we need to save child object i.e. address first and then save parent object.
//          session.save(address1);
//       	session.save(address2);
//       	session.save(employee1);
//       	session.save(employee2);

//If no CascadeType is given then below save will throw org.hibernate.TransientObjectException: object references an unsaved transient instance - save        	
//If Cascading is all then saving parent will save the child as well.
//       	session.save(employee1);
//       	session.save(employee2);

//If Cascading is persist then saving parent will save the child as well.
        	session.persist(employee1);
        	session.persist(employee2);
        
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
