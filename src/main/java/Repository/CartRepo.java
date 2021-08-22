package Repository;

import DB.DBConnectionPool;
import Dto.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepo {
    public String addcart(Cart cart) {
        ResultSet rs = null;
        ResultSet rsc = null;
        ResultSet rscn = null;
        ResultSet rsuid = null;
        int serviceid = 0;
        int id = 0;
        int cid = 0;
        double amount = 0;
        double stillamount = 0;
        double serviceamount = 0;
        Connection con = null;
        ResultSet rsservicename = null;
        PreparedStatement stmt = null;
        PreparedStatement stmtc = null;
        PreparedStatement stmtcn = null;
        PreparedStatement stmtcart = null;
        PreparedStatement stmtservicename = null;
        PreparedStatement stmtuid = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM user WHERE user.username = ?");
            stmtuid.setString(1, cart.getName());
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                id = rsuid.getInt("id");
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);

            stmtc = con.prepareStatement("SELECT * FROM cart WHERE cart.userid = ?");
            stmtc.setString(1, String.valueOf(id));
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                cid = rsc.getInt("cartid");
                stillamount = rsc.getDouble("amount");
            }
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);

            stmtservicename = con.prepareStatement("SELECT * FROM services WHERE services.servicename = ?");
            stmtservicename.setString(1, cart.getservicename());
            rsservicename = stmtservicename.executeQuery();
            while (rsservicename.next()) {
                serviceid = rsservicename.getInt("serviceid");
                serviceamount = rsservicename.getDouble("cost");
            }
            DBConnectionPool.getInstance().close(stmtservicename);
            DBConnectionPool.getInstance().close(rsservicename);

            if (cid == 0) {
                stmtcn = con.prepareStatement("SELECT * FROM cart ");
                rscn = stmtcn.executeQuery();
                while (rscn.next()) {
                    cid = rscn.getInt("cartid");
                }
                cid = cid + 1;
                DBConnectionPool.getInstance().close(stmtcn);
                DBConnectionPool.getInstance().close(rscn);

                stmt = con.prepareStatement("INSERT INTO cart (cartid, userid, amount) VALUES (?, ?, ?)");
                stmt.setString(1, String.valueOf(cid));
                stmt.setString(2, String.valueOf(id));
                stmt.setString(3, String.valueOf(stillamount + serviceamount));
                changedRow = stmt.executeUpdate();
                DBConnectionPool.getInstance().close(stmt);

            } else {
                stmt = con.prepareStatement("UPDATE cart SET amount = ? WHERE cartid = ?");
                stmt.setString(1, String.valueOf(stillamount + serviceamount));
                stmt.setString(2, String.valueOf(cid));
                changedRow = stmt.executeUpdate();
                DBConnectionPool.getInstance().close(stmt);
            }


            stmtcart = con.prepareStatement("INSERT INTO cart_service (cartid, serviceid, quantity) VALUES (?, ?, ?)");
            stmtcart.setString(1, String.valueOf(cid));
            stmtcart.setString(2, String.valueOf(serviceid));
            stmtcart.setString(3, String.valueOf(cart.getqty()));
            changedRow = stmtcart.executeUpdate();
            DBConnectionPool.getInstance().close(stmtcart);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(con);
        }
        return changedRow == 1 ? "Cart Recorded" : "Cart Recording failed";
    }

    public String[] findcart(Cart cart) {
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
            stmtuid.setString(1, cart.getName());
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                id = rsuid.getInt("id");
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);

            stmtc = con.prepareStatement("SELECT * FROM cart WHERE cart.userid = ?");
            stmtc.setString(1, String.valueOf(id));
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                cid = rsc.getInt("cartid");
            }
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);

            stmtco = con.prepareStatement("SELECT * FROM cart_service WHERE cart_service.cartid = ?");
            stmtco.setString(1, String.valueOf(cid));
            rsco = stmtco.executeQuery();
            while (rsco.next()) {
                cid = rsco.getInt("cartid");
            }
            DBConnectionPool.getInstance().close(stmtco);
            DBConnectionPool.getInstance().close(rsco);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DBConnectionPool.getInstance().close(con);
        }
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM cart_service WHERE cart_service.cartid = ?");
            stmtservice.setString(1, Integer.toString(cid));
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();
//            DBConnectionPool.getInstance().close(stmtservice);


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
                serviceqty[j] = rsservice.getInt("quantity");
                j++;
            }
            DBConnectionPool.getInstance().close(con);
            con = DBConnectionPool.getInstance().getConnection();
            for (int k = 0; k < i; k++) {

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

            }
            DBConnectionPool.getInstance().close(con);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(stmtservice);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
//            DBConnectionPool.getInstance().close(stmtservice);
//            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return servicename;
    }

    public int[] getqty(Cart cart) {
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
            stmtuid.setString(1, cart.getName());
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                id = rsuid.getInt("id");
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);

            stmtc = con.prepareStatement("SELECT * FROM cart WHERE cart.userid = ?");
            stmtc.setString(1, String.valueOf(id));
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                cid = rsc.getInt("cartid");
            }
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);

            stmtco = con.prepareStatement("SELECT * FROM cart_service WHERE cart_service.cartid = ?");
            stmtco.setString(1, String.valueOf(cid));
            rsco = stmtco.executeQuery();
            while (rsco.next()) {
                cid = rsco.getInt("cartid");
            }
            DBConnectionPool.getInstance().close(stmtco);
            DBConnectionPool.getInstance().close(rsco);
            DBConnectionPool.getInstance().close(con);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(con);
        }
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM cart_service WHERE cart_service.cartid = ?");
            stmtservice.setString(1, Integer.toString(cid));
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();
//            DBConnectionPool.getInstance().close(stmtservice);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service id");
            System.out.println(e);
        }
        int[] serviceid = new int[i];
        int[] serviceqty = new int[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                serviceid[j] = rsservice.getInt("serviceid");
                serviceqty[j] = rsservice.getInt("quantity");
                j++;
            }
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
//            DBConnectionPool.getInstance().close(stmtservice);
//            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return serviceqty;
    }
}
