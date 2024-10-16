package pj.xuanbao.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import pj.xuanbao.configs.JPAConfig;
import pj.xuanbao.dao.IVideoDAO;
import pj.xuanbao.entity.Category;
import pj.xuanbao.entity.Video;

public class VideoDAOImpl implements IVideoDAO{

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video); //insert
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
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video); //insert
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
	public void delete(int vidid) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video video = enma.find(Video.class, vidid);
			if(video != null) {
				enma.remove(video);
			}else {
				throw new Exception("The video doesn't exist");
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
	public Video findById(int vidid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, vidid);
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> vidlist = enma.createNamedQuery("Video.findAll", Video.class);
		return vidlist.getResultList();
	}

	@Override
	public List<Video> findByVideoName(String vidName) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select v from Video v where title like :vidname";
		TypedQuery<Video> vidlist = enma.createNamedQuery(jpql, Video.class);
		vidlist.setParameter("vidname", "%" + vidName + "%");
		return vidlist.getResultList();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> vidlist = enma.createNamedQuery("Video.findAll", Video.class);
		vidlist.setFirstResult(page*pagesize);
		vidlist.setMaxResults(pagesize);
		return vidlist.getResultList();
	}

	@Override
	public List<Video> findByCategory(String categoryname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select c from Category c where c.categoryname like :catename";
		TypedQuery<Category> query = enma.createNamedQuery(jpql, Category.class);
		query.setParameter("catename", "%" + categoryname + "%");
		Category category = (Category)query.getSingleResult();
		
		
		
		EntityManager enmaV = JPAConfig.getEntityManager();
		String jpqlV = "select v from Video v where categoryid = :id";
		TypedQuery<Video> vidlist = enmaV.createNamedQuery(jpqlV, Video.class);
		vidlist.setParameter("id", "%" + category.getCategoryId() + "%");
		return vidlist.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "select count(v) from Video v";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
