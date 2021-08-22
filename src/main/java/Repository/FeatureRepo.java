package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeatureRepo {
    public String[] fename(String name, String serid, String qty) {
        String[] featurename = new String[Integer.valueOf(qty)];
        Connection con = null;
        PreparedStatement stmtuid = null;
        ResultSet rsuid = null;
        int id=0;
        int i=0;

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

            PreparedStatement stmtfn = con.prepareStatement("SELECT * FROM feature WHERE feature.userid = ? and feature.serviceid = ?");
            stmtfn.setString(1, String.valueOf(id));
            stmtfn.setString(2, String.valueOf(serid));
            ResultSet rsufn = stmtfn.executeQuery();
            while (rsufn.next()) {
                featurename[i]=(rsufn.getString("feature_name"));
                i++;
            }
            DBConnectionPool.getInstance().close(stmtfn);
            DBConnectionPool.getInstance().close(rsufn);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return featurename;
    }

    public int[] feid(String name, String serid, String qty) {
        int[] featureid = new int[Integer.valueOf(qty)];
        Connection con = null;
        PreparedStatement stmtuid = null;
        ResultSet rsuid = null;
        int id=0;
        int i=0;

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

            PreparedStatement stmtfn = con.prepareStatement("SELECT * FROM feature WHERE feature.userid = ? and feature.serviceid = ?");
            stmtfn.setString(1, String.valueOf(id));
            stmtfn.setString(2, String.valueOf(serid));
            ResultSet rsufn = stmtfn.executeQuery();
            while (rsufn.next()) {
                featureid[i]=(rsufn.getInt("featureid"));
                i++;
            }
            DBConnectionPool.getInstance().close(stmtfn);
            DBConnectionPool.getInstance().close(rsufn);
            DBConnectionPool.getInstance().close(con);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return featureid;
    }
}
