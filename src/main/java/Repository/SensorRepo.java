package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorRepo {
    public boolean saveData(String sensorId, String featureid) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            System.out.println(date);
            stmt = con.prepareStatement("INSERT INTO sensor (sensorId, featureid, timeStamp ) VALUES (?, ?, ?)");
            stmt.setString(1, sensorId);
            stmt.setString(2, featureid);
            stmt.setTimestamp(3, date);

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
        return changedRow == 1;
    }
}
