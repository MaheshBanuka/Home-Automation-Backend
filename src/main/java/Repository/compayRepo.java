package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class compayRepo {

    public void compay(String name) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String datec = String.valueOf(java.time.LocalDate.now());
        String timec = String.valueOf(java.time.LocalTime.now());
        System.out.println(datec);
        System.out.println(timec);
        Connection con = null;
        PreparedStatement stmtuid = null;
        PreparedStatement stmt = null;
        PreparedStatement stmtdel = null;
        ResultSet rsuid = null;
        double amount = 0;
        int changedRow = 0;
        PreparedStatement stmtc = null;
        ResultSet rsc = null;
        int id = 0;
        int cid = 0;
        int oid = 0;
        int fid = 0;

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

            stmtc = con.prepareStatement("SELECT * FROM cart WHERE cart.userid = ?");
            stmtc.setString(1, String.valueOf(id));
            rsc = stmtc.executeQuery();
            while (rsc.next()) {
                cid = rsc.getInt("cartid");
                amount = rsc.getDouble("amount");
            }
            DBConnectionPool.getInstance().close(stmtc);
            DBConnectionPool.getInstance().close(rsc);

            PreparedStatement stmtcn = con.prepareStatement("SELECT * FROM `homeauto`.`order` ");
            ResultSet rscn = stmtcn.executeQuery();
            while (rscn.next()) {
                oid ++;
            }
            oid = oid + 1;
            DBConnectionPool.getInstance().close(stmtcn);
            DBConnectionPool.getInstance().close(rscn);

            stmt = con.prepareStatement("INSERT INTO `homeauto`.`order` (`orderid`, `customerid`, `amount`, `date`, `time`, `status`) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, String.valueOf(oid));
            stmt.setString(2, String.valueOf(id));
            stmt.setString(3, String.valueOf(amount));
            stmt.setString(4, String.valueOf(datec));
            stmt.setString(5, String.valueOf(timec));
            stmt.setString(6, "Paid");
            changedRow = stmt.executeUpdate();
            if (changedRow != 0) {
                stmtdel = con.prepareStatement("DELETE FROM cart WHERE cart.cartid=?");
                stmtdel.setString(1, String.valueOf(cid));
                changedRow = stmtdel.executeUpdate();
            }
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(stmtdel);

            PreparedStatement stmtcs = con.prepareStatement("SELECT * FROM cart_service WHERE cart_service.cartid = ? ");
            stmtcs.setString(1, String.valueOf(cid));
            ResultSet rscs = stmtcs.executeQuery();

            PreparedStatement stmtf = con.prepareStatement("SELECT * FROM feature ");
            ResultSet rsf = stmtf.executeQuery();
            while (rsf.next()){
                fid++;
            }
            DBConnectionPool.getInstance().close(stmtf);
            DBConnectionPool.getInstance().close(rsf);

            while (rscs.next()) {
                PreparedStatement stmtos = con.prepareStatement("INSERT INTO order_service (orderid,serviceid, qty) VALUES (?, ?, ?)");
                stmtos.setString(1, String.valueOf(oid));
                stmtos.setString(2, String.valueOf(rscs.getInt("serviceid")));
                stmtos.setString(3, String.valueOf(rscs.getInt("quantity")));
                changedRow = stmtos.executeUpdate();
                DBConnectionPool.getInstance().close(stmtos);

                for (int i=1;i<=rscs.getInt("quantity");i++){
                    fid = fid+1;
                    PreparedStatement stmtfi = con.prepareStatement("INSERT INTO feature (featureid,userid, serviceid, feature_name) VALUES (?, ?, ?, ?)");
                    stmtfi.setString(1, String.valueOf(fid));
                    stmtfi.setString(2, String.valueOf(id));
                    stmtfi.setString(3, String.valueOf(rscs.getInt("serviceid")));
                    stmtfi.setString(4, String.valueOf("Feature"+i));
                    changedRow = stmtfi.executeUpdate();
                    DBConnectionPool.getInstance().close(stmtfi);
                }
            }

            PreparedStatement stmtdelcs = con.prepareStatement("DELETE FROM cart_service WHERE cart_service.cartid=?");
            stmtdelcs.setString(1, String.valueOf(cid));
            changedRow = stmtdelcs.executeUpdate();

            DBConnectionPool.getInstance().close(stmtcs);
            DBConnectionPool.getInstance().close(stmtdelcs);
            DBConnectionPool.getInstance().close(rscs);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
