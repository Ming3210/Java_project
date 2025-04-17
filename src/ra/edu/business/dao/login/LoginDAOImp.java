package ra.edu.business.dao.login;

import ra.edu.business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginDAOImp implements LoginDAO {

    @Override
    public int checkLogin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        int returnCode = 0;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL check_login(?, ?, ?)}");

            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, Types.INTEGER);

            callSt.execute();

            returnCode = callSt.getInt(3); // 1: admin, 2: student, 0: invalid

        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return returnCode;
    }
}
