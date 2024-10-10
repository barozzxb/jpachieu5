package pj.xuanbao.services;

import java.util.List;

import pj.xuanbao.entity.Category;

public interface ICategoryService {
	void insert (Category category);
	void update (Category category);
	void delete (int cateid);
	
	Category findById(int cateid);
	List<Category> findAll();
	List<Category> findByCategoryName(String cateName);
	List<Category> findAll(int page, int pagesize);
	int count();
}
