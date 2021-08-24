package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewUserRepo {
    public String[] viewusername(){
        Connection con = null;
        PreparedStatement stmtuid = null;
        ResultSet rsuid = null;
        int count = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM user");
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                count++;
            }
            rsuid = stmtuid.executeQuery();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
        String[] usernames = new String[count];
        int j = 0;
        try{
            while (rsuid.next()) {
                usernames[j] = rsuid.getString("userName");
                j++;
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return usernames;
    }

    public int[] viewuserid(){
        Connection con = null;
        PreparedStatement stmtuid = null;
        ResultSet rsuid = null;
        int count = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM user");
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                count++;
            }
            rsuid = stmtuid.executeQuery();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
        int[] userid = new int[count];
        int j = 0;
        try{
            while (rsuid.next()) {
                userid[j] = rsuid.getInt("id");
                j++;
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return userid;
    }

    public String userbanned(int userid){
        PreparedStatement stmt = null;
        Connection con = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("UPDATE `homeauto`.`user` SET `status`='banned' WHERE  `id`=?");
            stmt.setString(1, String.valueOf(userid));
            changedRow = stmt.executeUpdate();
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("banned update failed");
        }
        return changedRow == 1 ? "Banned Success" : "Banned Failed";
    }

    public String useractive(int userid){
        PreparedStatement stmt = null;
        Connection con = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("UPDATE `homeauto`.`user` SET `status`='active' WHERE  `id`=?");
            stmt.setString(1, String.valueOf(userid));
            changedRow = stmt.executeUpdate();
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("banned update failed");
        }
        return changedRow == 1 ? "active Success" : "active Failed";
    }

    public String userdelete(int userid){
        PreparedStatement stmt = null;
        Connection con = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("DELETE FROM `homeauto`.`user` WHERE  `id`=?");
            stmt.setString(1, String.valueOf(userid));
            changedRow = stmt.executeUpdate();
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("banned update failed");
        }
        return changedRow == 1 ? "Delete Success" : "Delete Failed";
    }
}
