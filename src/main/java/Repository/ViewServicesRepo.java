package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewServicesRepo {
    public String[] viewservice(){
        int i=0;
        ResultSet rsservice = null;
        PreparedStatement stmtservice = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM services");
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();
        } catch (SQLException e) {
            System.out.println("service id");
            System.out.println(e);
        }
        int[] serviceid = new int[i];
        String[] servicename = new String[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                servicename[j] = rsservice.getString("servicename");
                j++;
            }
            DBConnectionPool.getInstance().close(con);
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
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
    public int[] viewserviceid(){
        int i=0;
        ResultSet rsservice = null;
        PreparedStatement stmtservice = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM services");
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();
        } catch (SQLException e) {
            System.out.println("service id");
            System.out.println(e);
        }
        int[] serviceid = new int[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                serviceid[j] = rsservice.getInt("serviceid");
                j++;
            }
            DBConnectionPool.getInstance().close(con);
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return serviceid;
    }
    public double[] viewservicecost(){
        int i=0;
        ResultSet rsservice = null;
        PreparedStatement stmtservice = null;
        Connection con = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("SELECT * FROM services");
            rsservice = stmtservice.executeQuery();
            i = 0;
            while (rsservice.next()) {
                i++;
            }
            rsservice = stmtservice.executeQuery();
        } catch (SQLException e) {
            System.out.println("service id");
            System.out.println(e);
        }
        double[] servicecost = new double[i];
        try {
            int j = 0;
            while (rsservice.next()) {
                servicecost[j] = rsservice.getDouble("cost");
                j++;
            }
            DBConnectionPool.getInstance().close(con);
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtservice);
            DBConnectionPool.getInstance().close(rsservice);
            DBConnectionPool.getInstance().close(con);
        }
        return servicecost;
    }

    public String updateservice(String id, String name, String cost){
        PreparedStatement stmtservice = null;
        Connection con = null;
        int changedRow = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("UPDATE services SET servicename = ?, cost = ? WHERE serviceid = ?");
            stmtservice.setString(1, String.valueOf(name));
            stmtservice.setString(2, String.valueOf(cost));
            stmtservice.setString(3, String.valueOf(id));
            changedRow = stmtservice.executeUpdate();
        } catch (SQLException e) {
            System.out.println("service id");
            System.out.println(e);
        }
        return changedRow == 1 ? "Service Details Updated" : "Service Details Update failed";
    }
}
