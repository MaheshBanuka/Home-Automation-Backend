package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeatureRenameRepo {
    public String ferename(String fid, String name){
        PreparedStatement stmtservice = null;
        Connection con = null;
        int changedRow = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtservice = con.prepareStatement("UPDATE `homeauto`.`feature` SET `feature_name`=? WHERE  `featureid`=?;");
            stmtservice.setString(1, String.valueOf(name));
            stmtservice.setString(2, String.valueOf(fid));
            changedRow = stmtservice.executeUpdate();
        } catch (SQLException e) {
            System.out.println("service id");
            System.out.println(e);
        }
        return changedRow == 1 ? "Service Details Updated" : "Service Details Update failed";
    }
}
