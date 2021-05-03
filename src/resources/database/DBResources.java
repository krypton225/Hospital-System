package resources.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBResources implements I_DataBaseInfo {

    public static Connection openConnectionWithDB() throws Exception {
        Class.forName(I_DataBaseInfo.ORACLE_DRIVER);

        return DriverManager.getConnection
                (I_DataBaseInfo.URL, I_DataBaseInfo.USER_NAME, I_DataBaseInfo.PASSWORD);
    }

    public static void closeConnection(Connection conn) throws Exception {
        conn.commit();
        conn.close();
    }
}
