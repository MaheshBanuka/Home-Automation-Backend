package Repository;
import DB.DBConnectionPool;

import java.io.IOException;
import Dto.User;
import Dto.Login;
import java.sql.*;

public class UserRepo {
    public String userRegister(User user) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();

            stmt = con.prepareStatement("INSERT INTO user (userName, email, phone, address, password) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getPassword());
            changedRow = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return changedRow == 1 ? "User Registerd" : "User registration failed";
    }

    public String userLogin(Login login) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        String name = null;
        String userName = login.getUserName();
        String password = login.getPassword();

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT userName FROM user WHERE user.username = ? AND user.password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()){
                name = rs.getString("userName");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return name;
    }
}
