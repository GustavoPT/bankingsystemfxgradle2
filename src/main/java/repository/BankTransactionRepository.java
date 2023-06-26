package repository;

import models.BankTransaction;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class BankTransactionRepository {

	public List<BankTransaction> getBankTransactions(String username) {
		Transaction transaction = null;
		List<BankTransaction> transactionList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select t from BankTransaction t where t.user.id = :id").setParameter("id", user.getId());
				transactionList = q.list();
			}
			else{
				System.out.println("User equals null in Bank DAO G");
			}
			transaction.commit();			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return transactionList;
	}
	
	public String saveTransaction(BankTransaction BankTransaction, String username) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				BankTransaction.setUser(user);
				session.save(BankTransaction);
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

	// update and delete methods add them to therepository
	public void updateTransaction(BankTransaction bankTransaction) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(bankTransaction);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteTransaction(BankTransaction bankTransaction) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(bankTransaction);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}



}