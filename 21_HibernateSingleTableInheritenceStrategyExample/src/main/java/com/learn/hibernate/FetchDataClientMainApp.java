package com.learn.hibernate;
import org.hibernate.Session;

import com.learn.hibernate.entity.Employee;
import com.learn.hibernate.entity.Person;
import com.learn.hibernate.entity.Student;
//Change loading to lazy if want to avoid join query.
public class FetchDataClientMainApp {
   public static void main(String[] args) {
	   Person person = null;
      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
    	  person = session.load(Person.class, 2);
    	  
    	  if(person instanceof Person && !(person instanceof Employee) && !(person instanceof Student))
    	  {
    		  System.out.println(person);
    	  }
    	  else if(person instanceof Person && (person instanceof Employee))
    	  {
    		  Employee employee = (Employee) person;
    		  System.out.println(employee);
    		  
    	  }
    	  else if(person instanceof Person && (person instanceof Student))
    	  {  
    		  Student student = (Student) person;
    		  System.out.println(student);
    	  }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      HibernateUtil.shutdown();
   }
}
