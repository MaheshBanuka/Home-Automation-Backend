package Repository;

import DB.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewSensorRepo {
    public String[] sensortime(String feid){
        Connection con = null;
        PreparedStatement stmtuid = null;
        ResultSet rsuid = null;
        int q = 0;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmtuid = con.prepareStatement("SELECT * FROM sensor WHERE sensor.featureid = ?");
            stmtuid.setString(1, feid);
            rsuid = stmtuid.executeQuery();
            while (rsuid.next()) {
                q++;
            }
            rsuid = stmtuid.executeQuery();
        }catch (Exception e){
            System.out.println(e);
        }
        String[] time = new String[q];
        int j = 0;
        try{
            while (rsuid.next()) {
                time[j] = String.valueOf(rsuid.getTimestamp("timeStamp"));
                j++;
            }
            DBConnectionPool.getInstance().close(stmtuid);
            DBConnectionPool.getInstance().close(rsuid);
            DBConnectionPool.getInstance().close(con);
        }catch (Exception e){
            System.out.println(e);
        }
        return time;
    }
}
