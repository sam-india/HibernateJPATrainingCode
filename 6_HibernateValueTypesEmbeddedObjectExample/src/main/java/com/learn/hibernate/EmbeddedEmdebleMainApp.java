package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Employee;

public class EmbeddedEmdebleMainApp {
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
         			System.out.println(employee.getAddress());
         		transaction.commit();
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
