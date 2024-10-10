package pj.xuanbao.services.impl;

import pj.xuanbao.dao.IUserDAO;
import pj.xuanbao.dao.impl.UserDAOImpl;
import pj.xuanbao.entity.User;
import pj.xuanbao.services.IUserServices;

public class UserServicesImpl implements IUserServices{


	IUserDAO userDAO = new UserDAOImpl();
	
	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	
	@Override
	public User login(String username, String password) {
		User user = this.findByUsername(username);
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDAO.checkExistUsername(username)) {
			return false;
		}
		userDAO.insert(new User(username, true, true, email, phone, fullname,password, null));
		return true;
	}
	
	@Override
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}
	
	@Override
	public boolean checkExistUsername(String username) {
		return userDAO.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDAO.checkExistPhone(phone);
	}
	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	public static void main(String[] args) {
		IUserServices service = new UserServicesImpl();
		User user = new User("xuanbao", true, true, "xuanbao@gmail.com", "0123654789", "Tran Xuan Bao", "xuanbao", null);
		service.insert(user);
		System.out.println(user.toString());
	}


	@Override
	public void resetPassword(String email, String password) {
		// TODO Auto-generated method stub
		
	}
}
