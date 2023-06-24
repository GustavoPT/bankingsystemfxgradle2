package util;


import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


// user -> c
// 
// delete 
// save 
// modify get
public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/bankingsystemfx2?allowPublicKeyRetrieval=true&useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "aa09");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				
//				configuration.addAnnotatedClass(Course.class);
//				configuration.addAnnotatedClass(Instructor.class);
//				configuration.addAnnotatedClass(InstructorDetail.class);
//		
				
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Account.class);
				configuration.addAnnotatedClass(Card.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Merchant.class);
				configuration.addAnnotatedClass(BankTransaction.class);

				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}