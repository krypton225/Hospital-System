package resources.main;

import resources.database.DBResources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Specialities {

    public static ArrayList<String> getAllSpecialities() throws Exception {
        ArrayList<String> listOfSpecialities = new ArrayList<>();
        Connection conn = DBResources.openConnectionWithDB();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT NAME_SPEC FROM HOSPITAL_SPECIALITY");

        while (resultSet.next()) {
            listOfSpecialities.add(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();
        DBResources.closeConnection(conn);

        return listOfSpecialities;
    }

    public static void printSpec(ArrayList<String> list) {
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            System.out.print(j + "- " + list.get(i) + "\t");
            if (i % 5 == 0 && j != 1) {
                System.out.println();
            }
        }
    }
}
