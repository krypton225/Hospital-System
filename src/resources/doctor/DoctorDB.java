package resources.doctor;

import resources.database.DBResources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoctorDB {

    public static void insertNewDoctor(Doctor doctor) throws Exception {
        if (doctor != null) {
            Connection connection = DBResources.openConnectionWithDB();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO HOSPITAL_DOCTOR" +
                            "(ID_DOCTOR, NAME_DOCTOR, AGE_DOCTOR, SPECIALIST_DOCTOR_FK, DEGREE_DOCTOR) " +
                            "VALUES (?,?,?,?,?)");

            statement.setInt(1, doctor.getId());
            statement.setString(2, doctor.getName());
            statement.setInt(3, doctor.getAge());
            statement.setInt(4, doctor.getSpecialty());
            statement.setString(5, doctor.getDegree());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } else {
            System.out.println("Sorry! you can't insert a new doctor now!");
        }
    }

    public static void deleteExistedDoctor(Doctor doctor) throws Exception {
        if (doctor != null) {
            Connection connection = DBResources.openConnectionWithDB();
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM HOSPITAL_DOCTOR WHERE NAME_DOCTOR = ?");

            statement.setString(1, doctor.getName());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } else {
            System.out.println("Sorry! you can't delete this doctor now!");
        }
    }

    public static void getAllDoctors() throws Exception {
        Connection connection = DBResources.openConnectionWithDB();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT NAME_DOCTOR, SPECIALIST_DOCTOR_FK FROM HOSPITAL_DOCTOR");

        while (resultSet.next()) {
            String nameDoctor = resultSet.getString(1);
            String specialistDoctor = resultSet.getString(2);
            System.out.println("Dr. " + nameDoctor + ".\nAnd Speciality is: " + specialistDoctor + ".\n");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    public static void getDoctorsForSpecificSpeciality(byte num) throws Exception {
        Connection connection = DBResources.openConnectionWithDB();

        PreparedStatement statement = connection.prepareStatement("SELECT NAME_DOCTOR FROM HOSPITAL_DOCTOR" +
                " WHERE SPECIALIST_DOCTOR_FK = ?");
        statement.setByte(1, num);


        ResultSet set = statement.executeQuery();

        if (!set.isBeforeFirst()) {
            System.out.println("There is no data!");
        } else {
            System.out.println("All doctors in the speciality number: " + num + " are:");
            while (set.next()) {
                System.out.print(set.getString(1) + " ");
            }
        }


        connection.close();
    }
}
