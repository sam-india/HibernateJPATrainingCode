package com.learn.hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Customer;
import com.learn.hibernate.entity.Employee;

public class MainAppSave {
   public static void main(String[] args) {
		    Session session = null;
		    Transaction transaction = null;
		    try {
		      session = HibernateUtil.getSessionFactory().openSession();
		      transaction = session.beginTransaction();
		      //transaction.begin();

		      Customer customer = new Customer();
		      customer.setId(1l);
		      customer.setName("Sunil");
		      session.save(customer);
		      session.save(customer);

		      transaction.commit();
		      transaction = session.beginTransaction();
		      //transaction.begin();
		      session.persist(customer);
		      transaction.commit();
		    } catch (Exception e) {
		      if (transaction != null) {
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
