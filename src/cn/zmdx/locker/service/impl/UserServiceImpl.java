package cn.zmdx.locker.service.impl;

import cn.zmdx.locker.dao.interfaces.UserDAO;
import cn.zmdx.locker.entity.User;
import cn.zmdx.locker.service.interfaces.UserService;
import cn.zmdx.locker.util.Encrypter;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub

			user.setPassword(Encrypter.md5(user.getPassword()));
			return userDAO.save(user);
		
	}

	@Override
	public boolean verifyUser(User user) {
		// TODO Auto-generated method stub
		String password = userDAO.getPassword(user);
		if (password == null)
			return false;
		// 对客户端传过来的密码进行加密后，与数据库中相应的密码进行比较
		if (password.equals(Encrypter.md5(user.getPassword())))
			return true;
		return false;
	}

}
