package pj.xuanbao.dao.impl;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import pj.xuanbao.configs.JPAConfig;
import pj.xuanbao.dao.IUserDAO;
import pj.xuanbao.entity.Category;
import pj.xuanbao.entity.User;

public class UserDAOImpl implements IUserDAO{

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> users = enma.createNamedQuery("Category.findAll", User.class);
		return users.getResultList();
	}

	@Override
	public User findOne(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, id);
		return user;
	}

	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, username);
		return user;
	}

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user); //insert
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user); //insert
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, id);
			if(user != null) {
				enma.remove(user);
			}else {
				throw new Exception("The category doesn't exist");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		IUserDAO uDao = new UserDAOImpl();
		User user = uDao.findByUsername(username);
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, email);
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, phone);
		if (user != null)
			return true;
		return false;
	}
	
}
