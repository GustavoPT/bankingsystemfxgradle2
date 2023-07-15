package repository;

import models.Merchant;
import models.Product;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepository {

	public List<Product> getProducts(String username) {
		Transaction transaction = null;
		List<Product> productsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select a from Product a");		
				productsList = q.list();
			}
			transaction.commit();			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return productsList;
	}
	
	public void deleteProduct(String username, int productId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("DELETE from Product where id = :id").setParameter("id", productId);
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
	
	public String saveProduct(String productName, Merchant merchant, String username) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			
			Product product = new Product(productName, merchant);
			session.save(product);
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
	
	public String saveProduct(Integer id, String productName, Merchant merchant) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int count = session.createQuery("Update Product U set U.name= :name, U.merchant= :merchant WHERE U.id = :id").setParameter("id", id).setParameter("name", productName)
					.setParameter("merchant", merchant).executeUpdate();
			if(count != 0) {
				
			} else {
				errorMsg = "Product could not be updated...";
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
	
	public Product getProduct(Integer id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Product existingProd = (Product) session.createQuery("FROM Product U WHERE U.id = :id").setParameter("id", id)
					.uniqueResult();
			if(existingProd != null) {
				return existingProd;
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
	
	public List<Product> getNewProducts(String username) {
		Transaction transaction = null;
		List<Product> newProductsList = new ArrayList<Product>();
		List<Product> productsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select a from Product a");		
				productsList = q.list();
			}
			
			for(Product p: productsList) {
				if(p.getBankTransactions()==null) {
					newProductsList.add(p);
				}
			}

			transaction.commit();	
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}		
		return newProductsList;
	}
}