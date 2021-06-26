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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            stmt = con.prepareStatement("SELECT * FROM user WHERE user.username = ? AND user.password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while (rs.next()) {
                name = rs.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        System.out.println(name);
        return name;

    }

    public String[] userservices(Login login) {
        ResultSet rsorderid = null;
        ResultSet rsservice = null;
        Connection con = null;
        PreparedStatement stmtorder = null;
        PreparedStatement stmtservice = null;
        int i = 0;
        int id=0;
        int orderid = 1;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String userName = login.getUserName();
        String password = login.getPassword();

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM user WHERE user.username = ? AND user.password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtorder = con.prepareStatement("SELECT * FROM `homeauto`.`order` WHERE `homeauto`.`order`.customerid = ?");
            stmtorder.setString(1, Integer.toString(id));
            rsorderid = stmtorder.executeQuery();
            while (rsorderid.next()) {
                orderid = rsorderid.getInt("orderid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("order id");
        }

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT serviceid FROM order_service WHERE order_service.orderid = ?");
            stmtservice.setString(1, Integer.toString(orderid));
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service id");
            System.out.println(e);
        }
        int[] serviceid = new int[i];
        String[] servicename = new String[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                serviceid[j] = rsservice.getInt("serviceid");
                j++;
            }

            for (int k = 0; k < i; k++) {
                con = DBConnectionPool.getInstance().getConnection();
                ResultSet rsservicename = null;
                PreparedStatement stmtservicename = null;
                stmtservicename = con.prepareStatement("SELECT servicename FROM services WHERE services.serviceid = ?");
                stmtservicename.setString(1, Integer.toString(serviceid[k]));
                rsservicename = stmtservicename.executeQuery();
                while (rsservicename.next()) {
                    servicename[k] = rsservicename.getString("servicename");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(rsorderid);
            DBConnectionPool.getInstance().close(stmtorder);
            DBConnectionPool.getInstance().close(con);
        }
        return servicename;
    }
}
