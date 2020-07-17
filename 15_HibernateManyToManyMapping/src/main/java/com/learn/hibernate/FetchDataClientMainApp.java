package com.learn.hibernate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.learn.hibernate.entity.Address;
import com.learn.hibernate.entity.Employee;
//Change loading to lazy if want to avoid join query.
public class FetchDataClientMainApp {
   public static void main(String[] args) {
	   Employee employee = null;
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//         employee = session.load(Employee.class, 1);
    	  session.getTransaction().begin();
    	  TypedQuery<Employee> query  = session.createQuery("SELECT E FROM Employee E");
    	  List<Employee> list = query.getResultList();
    	  System.out.println(list);
    	  list.get(0).getAddresses().add(new Address("st1","beijing","Echina",2546654L));
    	  session.getTransaction().commit();
//         System.out.println(employee.toString());
//         employee.getAddresses().stream().forEach(System.out::println);
//         
//         Address address = session.load(Address.class, 1);
//         System.out.println("address: "+address);
//         System.out.println("address.getEmployee() "+address.getEmployee());
//         System.out.println("-------");
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      HibernateUtil.shutdown();
   }
}
