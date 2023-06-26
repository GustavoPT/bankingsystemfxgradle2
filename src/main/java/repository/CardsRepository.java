package repository;

import models.Card;
import models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CardsRepository {

	public List<Card> getCards(String username) {
		Transaction transaction = null;
		List<Card> cardsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("select a from Card a where a.user.id = :id").setParameter("id", user.getId());			
				cardsList = q.list();
			}
			transaction.commit();			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cardsList;
	}
	
	public void deleteCard(String username, int cardId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			if(user != null) {
				Query q = session.createQuery("DELETE from Card where id = :id").setParameter("id", cardId);
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
	
	public String saveCard(Card card, String username) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			User user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
					.uniqueResult();
			Card existingCard = (Card) session.createQuery("FROM Card U WHERE U.id = :id").setParameter("id", card.getId())
					.uniqueResult();
			if(existingCard == null) {
				card.setUser(user);
				session.save(card);
			} else {
				errorMsg = "Card aleady exists...";
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
	
	public String saveCard(Integer id, String cardNumber, String name, String cvv, String month, String year, String image) {
		Transaction transaction = null;
		String errorMsg = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			int count = session.createQuery("Update Card U set U.number= :number, U.name= :name, U.code= :code, U.month= :month, U.year= :year, U.image= :image WHERE U.id = :id")
					.setParameter("id", id).setParameter("number", cardNumber).setParameter("name", name).setParameter("code", cvv).setParameter("month", month)
					.setParameter("year", year).setParameter("image", image)
					.executeUpdate();
			if(count != 0) {
				
			} else {
				errorMsg = "Card could not be updated...";
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
	
	public Card getCard(Integer id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Card existingCard = (Card) session.createQuery("FROM Card U WHERE U.id = :id").setParameter("id", id)
					.uniqueResult();
			if(existingCard != null) {
				return existingCard;
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
}