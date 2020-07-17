package com.learn.hibernate;
import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.EmployeeDetail;
import com.learn.hibernate.entity.Employee;

public class MainApp2 {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();
         Employee employee = new Employee();
         //employee.setId(1L);
         employee.setEmpname("USR002");
         employee.setPassword("mno@xyz.com");

         EmployeeDetail employeeDetail = new EmployeeDetail();
        // employeeDetail.setId(1L);
         employeeDetail.setFirstName("sajal");
         employeeDetail.setLastName("sam");
         employeeDetail.setEmail("sajal.sam@example.com");
         employeeDetail.setDob(LocalDate.of(1985, Month.APRIL, 1));

         employee.setEmpDetail(employeeDetail);
         employeeDetail.setEmployee(employee);
         session.persist(employee);
         transaction.commit();
         System.out.println("User saved successfully");
         
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
