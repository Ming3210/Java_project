package ra.edu.business.service.login;


import ra.edu.business.dao.login.LoginDAO;
import ra.edu.business.dao.login.LoginDAOImp;

public class LoginServiceImp implements LoginService{
    private final LoginDAO loginDAO;


    public LoginServiceImp() {
        loginDAO = new LoginDAOImp();
    }


    @Override
    public int checkLogin(String username, String password) {
        return loginDAO.checkLogin(username, password);
    }



}
