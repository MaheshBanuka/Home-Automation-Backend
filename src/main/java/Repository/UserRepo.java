package Repository;
import DB.DBConnectionPool;

import java.io.IOException;
import Dto.User;
import java.sql.*;

public class UserRepo {
    public String userRegister(User user) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            System.out.println(user);
            stmt = con.prepareStatement("INSERT INTO user (userName, password) VALUES (?, ?)");
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            changedRow = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return changedRow == 1 ? "User Registerd" : "User registration failed";
    }

    public String userLogin(String userName, String password) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        String name = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT userName FROM user WHERE user.username = ? AND user.password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()){
                name = rs.getString("name");
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
