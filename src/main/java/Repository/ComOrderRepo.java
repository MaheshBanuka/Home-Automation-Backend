package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ComOrderRepo {
    public String comorder(String id) {
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRow = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("UPDATE `homeauto`.`order` SET `status`='completed' WHERE  `orderid`=?");
            stmt.setString(1, String.valueOf(id));
            changedRow = stmt.executeUpdate();
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return changedRow == 1 ? "Order Updated" : "Order Updating failed";
    }
}
