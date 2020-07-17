package com.learn.hibernate;
import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Employee;
import com.learn.hibernate.entity.Person;
import com.learn.hibernate.entity.Student;

public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Person person = new Person();
         person.setName("Elena Salvator");
         person.setGender("Female");
         
         Employee employee = new Employee();
         employee.setBonus(BigDecimal.valueOf(277.389));
         employee.setDeptName("IT");
         employee.setDoj(HibernateUtil.getDoj("18/12/2015"));
         employee.setEmail("Daemon@vp.com");
         employee.setSalary(8000.88);
         employee.setGender("Male");
         employee.setName("Daemon Salvator");
         
         Student student = new Student();
         student.setName("Katherine Pierce");
         student.setGender("Female");
         student.setFee(20000.50f);
         student.setSchoolName("DPS");
         student.setSectionName("12th Std");
         
         session.save(person);
         session.save(employee);
         session.save(student);
         
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
