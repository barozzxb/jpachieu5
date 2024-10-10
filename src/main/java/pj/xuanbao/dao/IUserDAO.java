package pj.xuanbao.dao;

import java.util.List;

import pj.xuanbao.entity.User;

public interface IUserDAO {
	List<User> findAll();
	
	User findOne(int id);
	
	User findByUsername(String username);
	
	void insert(User user);
	void update(User user);
	void delete(int id);
	
	boolean checkExistUsername(String username);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
}
