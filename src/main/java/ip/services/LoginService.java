package ip.services;

import ip.beans.UserBean;
import ip.dao.UserDAO;

public class LoginService {

	private static LoginService loginService;
	
	private LoginService() {}
	
	public static LoginService getLoginService() {
		if(loginService == null) {
			loginService = new LoginService();
		}
		return loginService;
	}
	
	public UserBean loginUser(String username, String password) {
		return UserDAO.checkCredentials(username, password);
	}
	
}
