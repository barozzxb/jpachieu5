package pj.xuanbao.services;

import pj.xuanbao.entity.User;

public interface IUserServices {
	User login(String username, String password);

	User findByUsername(String username);

	boolean register(String email, String password, String username, String fullname, String phone);

	public boolean checkExistEmail(String email);

	public boolean checkExistUsername(String username);

	public boolean checkExistPhone(String phone);

	public void insert(User user);

	public void resetPassword(String email, String password);

	public void update(User user);
}
