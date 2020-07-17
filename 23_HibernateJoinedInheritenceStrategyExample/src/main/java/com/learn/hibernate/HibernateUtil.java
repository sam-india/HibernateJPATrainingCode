package com.learn.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.learn.hibernate.entity.Employee;
import com.learn.hibernate.entity.Person;
import com.learn.hibernate.entity.Student;

public class HibernateUtil {
   private static StandardServiceRegistry registry;
   private static SessionFactory sessionFactory;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            Map<String, String> settings = new HashMap<>();
            settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernatedb");
            settings.put("hibernate.connection.username", "devuser");
            settings.put("hibernate.connection.password", "pass");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "update");
            settings.put("hibernate.format_sql", "true");
            settings.put("hibernate.current_session_context_class", "thread");
            registry = registryBuilder.applySettings(settings).build();

            MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(Employee.class);
            sources.addAnnotatedClass(Person.class);
            sources.addAnnotatedClass(Student.class);


            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
         } catch (Exception e) {
            System.out.println("SessionFactory creation failed");
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
         }
      }
      return sessionFactory;
   }

   public static void shutdown() {
      if (registry != null) {
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }

   public static Date getDoj(String doj)
   {
	   Date date = null;
	   try {
		   date = new SimpleDateFormat("dd/mm/yyyy").parse(doj);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return date;
   }

   
   
}
