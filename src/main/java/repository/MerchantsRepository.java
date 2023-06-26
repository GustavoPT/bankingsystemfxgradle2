package repository;

import models.Merchant;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;


public class MerchantsRepository {

	public List<Merchant> getMerchants(String username) {
		Transaction transaction = null;
		List<Merchant> merchantsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select t from Merchant t");			
				merchantsList = q.list();
			};
			transaction.commit();			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return merchantsList;
	}
	
	public String deleteMerchant(String username, int merchantId) {
		String message = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {				
				Merchant existingMerchant = (Merchant) session.createQuery("FROM Merchant U WHERE U.id = :id").setParameter("id", merchantId)
						.uniqueResult();
				if(existingMerchant.getProducts().size() > 0) {
					message = "Merchant cannot be deleted. Active products available.";
				} else {
				Query q = session.createQuery("DELETE from Merchant where id = :id").setParameter("id", merchantId);
				q.executeUpdate();
				}
			}
			transaction.commit();			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return "Error occurred...";
		}
		return message;
	}
	
	public Merchant getMerchant(Integer id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Merchant existingMerchant = (Merchant) session.createQuery("FROM Merchant U WHERE U.id = :id").setParameter("id", id)
					.uniqueResult();
			if(existingMerchant != null) {
				return existingMerchant;
			}
			transaction.commit();			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public String saveMerchant(Integer id, String name) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int count = session.createQuery("Update Merchant U set U.name= :name WHERE U.id = :id").setParameter("id", id).setParameter("name", name)
					.executeUpdate();
			if(count != 0) {
				
			} else {
				errorMsg = "Merchant could not be updated...";
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
	
	public String saveMerchant(Merchant merchant, String username) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			Merchant existingMerchant = (Merchant) session.createQuery("FROM Merchant U WHERE U.id = :id").setParameter("id", merchant.getId())
					.uniqueResult();
			if(existingMerchant == null) {
				session.save(merchant);
			} else {
				errorMsg = "Merchant aleady exists...";
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