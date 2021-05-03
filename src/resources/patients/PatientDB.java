package resources.patients;

import resources.database.DBResources;
import resources.doctor.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientDB {
    public static void insertNewPatient(Patient patient) throws Exception {
        if (patient != null) {
            Connection connection = DBResources.openConnectionWithDB();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO HOSPITAL_PATIENT" +
                            "(ID_PATIENT, NAME_PATIENT, AGE_PATIENT, STATUS_PATIENT, SPECIALIZATION_FK) " +
                            "VALUES (?,?,?,?,?)");

            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getName());
            statement.setInt(3, patient.getAge());
            statement.setInt(4, patient.getStatus());
            statement.setInt(5, patient.getSpecialityPatient());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } else {
            System.out.println("Sorry! you can't insert a new patient now!");
        }
    }

    public static void deleteExistedPatient(Patient patient) throws Exception {
        if (patient != null) {
            Connection connection = DBResources.openConnectionWithDB();
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM HOSPITAL_PATIENT WHERE NAME_PATIENT = ?");

            statement.setString(1, patient.getName());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } else {
            System.out.println("Sorry! you can't delete this doctor now!");
        }
    }

    public static void printAllPatientsInSystem() throws Exception {
        Connection connection = DBResources.openConnectionWithDB();
        if (connection != null) {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT NAME_PATIENT FROM HOSPITAL_PATIENT ORDER BY STATUS_PATIENT DESC");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("\n\nAll patients in system are: ");
                while (resultSet.next()) {
                    String allNames = resultSet.getString(1);
                    System.out.println(allNames);
                    PatientOperations.addThePastPatientsInList(allNames);
                }
            } else {
                System.out.println("There is no any patients!");
            }


            statement.close();
            connection.close();
        } else {
            System.out.println("Sorry! you can't delete this doctor now!");
        }
    }
}
