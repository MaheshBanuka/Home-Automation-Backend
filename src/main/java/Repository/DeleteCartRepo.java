package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCartRepo {
    public void cartdelete(String username, String sername, String qty){
        ResultSet rsc = null;
//        ResultSet rsco = null;
        ResultSet rsuid = null;
//        ResultSet rsservice = null;
        int id = 0;
        int sid = 0;
        int cid = 0;
        double stillamount = 0;
        double cost =0;
        Connection con = null;
        PreparedStatement stmtc = null;
        PreparedStatement stmtdel = null;
        PreparedStatement stmtuid = null;
        PreparedStatement stmtcup = null;
        ResultSet rsservicename = null;
        PreparedStatement stmtservicename = null;
        int changedRow = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM user WHERE user.username = ?");
            stmtuid.setString(1, username);
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
            stmtservicename.setString(1,sername );
            rsservicename = stmtservicename.executeQuery();
            while (rsservicename.next()) {
                sid = rsservicename.getInt("serviceid");
                cost = rsservicename.getDouble("cost");
            }
            System.out.println(sid);
            DBConnectionPool.getInstance().close(stmtservicename);
            DBConnectionPool.getInstance().close(rsservicename);

            stmtdel = con.prepareStatement("DELETE FROM `homeauto`.`cart_service` WHERE  `cartid`=? AND `serviceid`=?;");
            stmtdel.setString(1, String.valueOf(cid));
            stmtdel.setString(2, String.valueOf(sid));
            changedRow = stmtdel.executeUpdate();
            DBConnectionPool.getInstance().close(stmtdel);

            System.out.println(stillamount);
            System.out.println(cost);
            System.out.println(qty);

            stmtcup = con.prepareStatement("UPDATE cart SET amount = ? WHERE cartid = ?");
            stmtcup.setString(1, String.valueOf(stillamount - (cost*Integer.valueOf(qty))));
            stmtcup.setString(2, String.valueOf(cid));
            changedRow = stmtcup.executeUpdate();
            DBConnectionPool.getInstance().close(stmtcup);

            DBConnectionPool.getInstance().close(con);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
