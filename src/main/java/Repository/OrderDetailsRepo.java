package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsRepo {
    public int[] vieworderid(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                i++;
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
        }
        int[] orderid = new int[i];
        try {
            int j = 0;
            while (rsc.next()) {
                orderid[j] = rsc.getInt("orderid");
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderid;
    }
    public int[] viewuserid(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                i++;
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        int[] userid = new int[i];
        try {
            int j = 0;
            while (rsc.next()) {
                userid[j] = rsc.getInt("customerid");
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return userid;
    }
    public String[] vieworderdate(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                i++;
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        String[] orderdate = new String[i];
        try {
            int j = 0;
            while (rsc.next()) {
                orderdate[j] = rsc.getString("date");
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderdate;
    }
    public String[] vieworderstatus(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                i++;
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        String[] orderstatus = new String[i];
        try {
            int j = 0;
            while (rsc.next()) {
                orderstatus[j] = rsc.getString("status");
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderstatus;
    }
    public String[] viewnotcomorderstatus(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    i++;
                }
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        String[] orderstatus = new String[i];
        try {
            int j = 0;
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    orderstatus[j] = rsc.getString("status");
                    j++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderstatus;
    }
    public String[] viewnotcomorderdates(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    i++;
                }
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        String[] orderdates = new String[i];
        try {
            int j = 0;
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    orderdates[j] = rsc.getString("date");
                    j++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderdates;
    }
    public int[] viewnotcomorderid(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    i++;
                }
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        int[] orderid = new int[i];
        try {
            int j = 0;
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    orderid[j] = rsc.getInt("orderid");
                    j++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return orderid;
    }
    public int[] viewnotcomuserid(){
        ResultSet rsc = null;
        Connection con = null;
        PreparedStatement stmtc = null;
        int i = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtc = con.prepareStatement("SELECT * FROM `homeauto`.`order`");
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    i++;
                }
            }
            rsc = stmtc.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {

        }
        int[] userid = new int[i];
        try {
            int j = 0;
            while (rsc.next()) {
                if(!rsc.getString("status").equals("completed")){
                    userid[j] = rsc.getInt("customerid");
                    j++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("service name");
        } finally {
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);
            DBConnectionPool.getInstance().close(con);
        }
        return userid;
    }
}
