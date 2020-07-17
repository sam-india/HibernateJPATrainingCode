package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Employee;

public class DirtyCheckMainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         Employee employee = session.get(Employee.class, 1);
         transaction = session.getTransaction();
         	if(employee != null)
         	{
         		transaction.begin();
         		employee.setSalary(65000.00);
         		
//         		session.update(employee); 
//All the entity object present in persistent context are tracked for further changes and all the further changes 
// will be commited to DB once the commit method gets called without using any update method, this concept is called dirty checking mechanism
         		transaction.commit();
         		employee.setEmail("scott@lnt.com"); //wILL NOT GET SAVED
         		System.out.println("Employee Data saved successfully");
         	}
         	else {
                System.out.println("Employee Data Doesn't Exist");
			}

         
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
