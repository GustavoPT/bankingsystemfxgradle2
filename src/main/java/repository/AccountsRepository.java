package repository;

import models.Account;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;


public class AccountsRepository {

	public List<Account> getAccounts(String username) {
		Transaction transaction = null;
		List<Account> accountsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select a from Account a where a.user.id = :id").setParameter("id", user.getId());			
				accountsList = q.list();
			}
			transaction.commit();			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return accountsList;
	}
	
	public void deleteAccount(String username, int accountId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("DELETE from Account where id = :id").setParameter("id", accountId);
				q.executeUpdate();
			}
			transaction.commit();			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public String saveAccount(Account account, String username) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			Account existingAcc = (Account) session.createQuery("FROM Account U WHERE U.id = :id").setParameter("id", account.getId())
					.uniqueResult();
			if(existingAcc == null) {
				account.setUser(user);
				session.save(account);
			} else {
				errorMsg = "Account aleady exists...";
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
	
	public String saveAccount(Integer id, String accountType) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int count = session.createQuery("Update Account U set U.type= :type WHERE U.id = :id").setParameter("id", id).setParameter("type", accountType)
					.executeUpdate();
			if(count != 0) {
				
			} else {
				errorMsg = "Account type could not be updated...";
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
	
	public Account getAccount(Integer id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Account existingAcc = (Account) session.createQuery("FROM Account U WHERE U.id = :id").setParameter("id", id)
					.uniqueResult();
			if(existingAcc != null) {
				return existingAcc;
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
	
	public String saveAccountBalance(Integer id, Double amount) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int count = session.createQuery("Update Account U set U.balance= :balance WHERE U.id = :id").setParameter("id", id).setParameter("balance", amount)
					.executeUpdate();
			if(count != 0) {
				
			} else {
				errorMsg = "Account balance could not be updated...";
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