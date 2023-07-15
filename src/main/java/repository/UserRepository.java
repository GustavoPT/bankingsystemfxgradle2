package repository;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserRepository {

	public String saveUser(User user) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User existingUser = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", user.getUsername())
					.uniqueResult();
			if(existingUser == null) {
				session.save(user);
			} else {
				errorMsg = "Username aleady exists...";
			}
			transaction.commit();			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			errorMsg = "Error occurred...";
		}
		return errorMsg;
	}

	public boolean validate(String userName, String password) {

		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", userName)
					.uniqueResult();
			
			if(user != null && user.getPassword().equals(password)) {
				return true;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
	
	public User getUser(String userName) {

		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", userName)
					.uniqueResult();
			
			if(user != null) {
				return user;
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}
	
	public String modifyUser(User user, Integer id) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User existingUser = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", user.getUsername())
					.uniqueResult();
			if(existingUser != null && existingUser.getId() == id) {
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setUsername(user.getUsername());
				existingUser.setPassword(user.getPassword());
				session.save(existingUser);
			} else if (existingUser != null && existingUser.getId() != id) {
				errorMsg = "Username aleady exists...";
			} else if(existingUser == null) {
				User existingUserById = (User) session.createQuery("FROM User U WHERE U.id = :id").setParameter("id", id)
						.uniqueResult();
				existingUserById.setFirstName(user.getFirstName());
				existingUserById.setLastName(user.getLastName());
				existingUserById.setUsername(user.getUsername());
				existingUserById.setPassword(user.getPassword());
				session.save(existingUserById);
			}
			transaction.commit();			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			errorMsg = "Error occurred...";
		}
		return errorMsg;
	}

}