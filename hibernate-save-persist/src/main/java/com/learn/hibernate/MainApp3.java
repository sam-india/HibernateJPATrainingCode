package com.learn.hibernate;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.FetchType;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;

import com.learn.hibernate.entity.EmployeeDetail;
import com.learn.hibernate.entity.Employee;

//fetch = FetchType.EAGER 
//Hibernate: select employee0_.EMP_ID as EMP_ID1_1_0_, employee0_.EMP_NAME as EMP_NAME2_1_0_, employee0_.PASSWORD as PASSWORD3_1_0_, employeede1_.EMP_DET_ID as EMP_DET_1_0_1_, employeede1_.DOB as DOB2_0_1_, employeede1_.EMAIL as EMAIL3_0_1_, employeede1_.EMP_ID as EMP_ID6_0_1_, employeede1_.FIRST_NAME as FIRST_NA4_0_1_, employeede1_.LAST_NAME as LAST_NAM5_0_1_ from EMPLOYEES_2 employee0_ left outer join EMP_DETAILS_2 employeede1_ on employee0_.EMP_ID=employeede1_.EMP_ID where employee0_.EMP_ID=?

//FetchType.LAZY
//Hibernate: select employee0_.EMP_ID as EMP_ID1_1_0_, employee0_.EMP_NAME as EMP_NAME2_1_0_, employee0_.PASSWORD as PASSWORD3_1_0_ from EMPLOYEES_2 employee0_ where employee0_.EMP_ID=?
//Hibernate: select employeede0_.EMP_DET_ID as EMP_DET_1_0_0_, employeede0_.DOB as DOB2_0_0_, employeede0_.EMAIL as EMAIL3_0_0_, employeede0_.EMP_ID as EMP_ID6_0_0_, employeede0_.FIRST_NAME as FIRST_NA4_0_0_, employeede0_.LAST_NAME as LAST_NAM5_0_0_ from EMP_DETAILS_2 employeede0_ where employeede0_.EMP_ID=?

public class MainApp3 {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Employee employee2 = session.load(Employee.class, 1L);
//         Employee employee2 = session.byId(Employee.class).getReference(1L);
         
         System.out.println(employee2.toString());
         employee2.getEmpDetail().setFirstName("Changes");
//         Employee employee = initializeAndUnproxy(employee2);
         
         transaction.commit();
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
   
   public static <T> T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}
   
}
