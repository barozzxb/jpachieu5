package pj.xuanbao.services.impl;

import java.util.List;

import pj.xuanbao.dao.ICategoryDAO;
import pj.xuanbao.dao.impl.CategoryDAOImpl;
import pj.xuanbao.entity.Category;
import pj.xuanbao.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	public ICategoryDAO cateDao = new CategoryDAOImpl();
	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
	}

	@Override
	public void delete(int cateid) {
		cateDao.update(null);
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findByCategoryName(String cateName) {
		return cateDao.findByCategoryName(cateName);
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return cateDao.count();
	}

}
