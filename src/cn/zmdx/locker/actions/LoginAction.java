package cn.zmdx.locker.actions;

import cn.zmdx.locker.entity.User;
import cn.zmdx.locker.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserServiceImpl userService;
	private String result;

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public String getResult() {
		return result;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public String execute() {
		if (userService.verifyUser(user))
			result = "成功";
		else
			result = "登陆失败";

		return SUCCESS;
	}

}
