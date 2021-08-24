package Repository;

import DB.DBConnectionPool;
import Dto.Cart;
import Dto.Login;
import sun.security.mscapi.CPublicKey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Myser {
    public String[] mysername(String name) {
        ResultSet rsc = null;
        ResultSet rsco = null;
        ResultSet rsuid = null;
        ResultSet rsservice = null;
        int id = 0;
        int cid = 0;
        Connection con = null;
        PreparedStatement stmtc = null;
        PreparedStatement stmtco = null;
        PreparedStatement stmtuid = null;
        PreparedStatement stmtservice = null;
        int changedRow = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM user WHERE user.username = ?");
            stmtuid.setString(1, name);
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                id = rsuid.getInt("id");
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);

            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order` WHERE order.customerid = ?");
            stmtc.setString(1, String.valueOf(id));
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                cid = rsc.getInt("orderid");
            }

            stmtco = con.prepareStatement("SELECT * FROM order_service WHERE order_service.orderid = ?");
            stmtco.setString(1, String.valueOf(cid));
            rsco = stmtco.executeQuery();
            while (rsco.next()) {
                cid = rsco.getInt("orderid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(stmtco);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(rsco);
            DBConnectionPool.getInstance().close(con);
        }
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM order_service WHERE order_service.orderid = ?");
            stmtservice.setString(1, Integer.toString(cid));
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
        int[] serviceqty = new int[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                serviceid[j] = rsservice.getInt("serviceid");
                serviceqty[j] = rsservice.getInt("qty");
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
                DBConnectionPool.getInstance().close(stmtservicename);
                DBConnectionPool.getInstance().close(rsservicename);
                DBConnectionPool.getInstance().close(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return servicename;
    }

    public int[] getqtyor(String name){
        ResultSet rsorderid = null;
        ResultSet rsservice = null;
        Connection con = null;
        PreparedStatement stmtorder = null;
        PreparedStatement stmtservice = null;
        int i = 0;
        int id=0;
        int orderid = 0;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT * FROM user WHERE user.username = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(con);
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
            DBConnectionPool.getInstance().close(stmtorder);
            DBConnectionPool.getInstance().close(rsorderid);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("order id");
        }

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM order_service WHERE order_service.orderid = ?");
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
        int[] serviceqty = new int[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                serviceqty[j] = rsservice.getInt("qty");
                j++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(rsorderid);
            DBConnectionPool.getInstance().close(stmtorder);
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return serviceqty;
    }
}
