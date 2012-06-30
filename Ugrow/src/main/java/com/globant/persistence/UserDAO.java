package com.globant.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.globant.entities.User;

@Repository
public class UserDAO {
	public static final int RESULTS = 20;

	private EntityManager eM;

	public EntityManager getEntityManager() {
		return eM;
	}

	public void setEntityManager(EntityManager eManager) {
		this.eM = eManager;
	}

	@Autowired
	public UserDAO(EntityManagerFactory eMF) {
		setEntityManager(eMF.createEntityManager());
	}

	public void save(User user) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(user);
		getEntityManager().getTransaction().commit();
	}

	public void delete(User user) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(user);
		getEntityManager().getTransaction().commit();
	}

	public User load(Integer id) {
		getEntityManager().getTransaction().begin();
		User user = getEntityManager().find(User.class, id);
		getEntityManager().getTransaction().commit();
		return user;
	}

	public User getUserByName(String username) {
		Query q = getEntityManager().createQuery("FROM User u WHERE u.username=:username");
		return (User) q.setParameter("username", username).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> find(String value, String orderIndex, String order,
			int pag) {
		Query q = getEntityManager()
				.createQuery(
						"FROM User u WHERE u.name LIKE :value OR u.company LIKE :value OR u.experience LIKE :value ORDER BY u."
								+ orderIndex + " " + order);
		return q.setFirstResult(pag * RESULTS).setMaxResults(RESULTS)
				.setParameter("value", "%" + value + "%").getResultList();
	}

	public Long count(String value) {
		return (Long) getEntityManager()
				.createQuery(
						"SELECT COUNT(*) FROM User u WHERE u.name LIKE :value OR u.company LIKE :value OR u.experience LIKE :value")
				.setParameter("value", "%" + value + "%").getSingleResult();
	}
}