package testing_app;

import org.apache.commons.lang3.text.WordUtils;
import resources.doctor.*;
import resources.main.Specialities;
import resources.patients.*;

import java.util.ArrayList;
import java.util.Scanner;

import static resources.patients.PatientDB.printAllPatientsInSystem;

public class OperationForMainClass {

    private final static Scanner s = new Scanner(System.in);

    public static void choseTheOperation() throws Exception {
        boolean flag = true;
        while (flag) {
            System.out.println("\n\n\nChoose the operation that you want: ");
            System.out.print("1- Add new patient.\t" +
                    "2- Delete patient.\t" +
                    "3- Print the next patient of today.\t\n" +
                    "4- Print all patients in other days and add them to patients of today.\n" +
                    "5- Print all patients in the system (Today and other days).\t" +
                    "6- Getting the doctors of specific speciality.\t\n" +
                    "7- Add a new doctor.\t" +
                    "8- Print all doctors. \t" +
                    "9- Delete a doctor.\t" +
                    "10- Exit.\n\n");

            System.out.print("Your choice is: ");
            byte x = s.nextByte();

            if (x == 1) {
                System.out.println("OK, YOUR CHOICE IS: ADDING NEW PATIENT!");
                forAddingNewPatientInSystem();
            } else if (x == 2) {
                System.out.println("OK, YOUR CHOICE IS: DELETING A PATIENT!");
                PatientOperations.deletePatientFromListAndDB();
            } else if (x == 3) {
                System.out.println("OK, YOUR CHOICE IS: PRINT THE NEXT PATIENT OF TODAY.");
                PatientOperations.getNextPatient();
            } else if (x == 4) {
                PatientDB.printAllPatientsInSystem();
            } else if (x == 5) {
                System.out.println("OK, YOUR CHOICE IS: PRINT ALL PATIENTS IN SYSTEM.");
                PatientOperations.printAllPatientsOfToday();
            } else if (x == 6) {
                System.out.println("OK, YOUR CHOICE IS: GETTING THE DOCTORS OF SPECIFIC SPECIALITY!");
                System.out.print("Choose the number of the speciality you want: ");
                byte num = s.nextByte();
                Specialities.getAllSpecialities();
                DoctorDB.getDoctorsForSpecificSpeciality(num);
            } else if (x == 7) {
                System.out.println("OK, YOUR CHOICE IS: ADDING A NEW DOCTOR!");
                addNewDoctorInSystem();
            } else if (x == 8) {
                System.out.println("OK, YOUR CHOICE IS: PRINTING ALL DOCTORS IN SYSTEM!");
                DoctorDB.getAllDoctors();
            } else if (x == 9) {
                System.out.println("OK, YOUR CHOICE IS: DELETING A DOCTOR!");
                deleteDoctorInSystem();
            } else if (x == 10) {
                System.out.println("OK, YOUR CHOICE IS: CLOSING THE SYSTEM!");
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.print("*");
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                flag = false;
                break;
            }
        }
    }

    public static void forAddingNewPatientInSystem() throws Exception {
        boolean f = true;
        do {
            PatientOperations.addPatientToListAndDB();

            System.out.print("Do you want to add another patient? (Y/y for yes, N/n for no) : ");
            String x = s.next();

            if (x.equals("N") || x.equals("n")) {
                f = false;
                break;
            }
        } while (f);
    }

    public static void addNewDoctorInSystem() throws Exception {
        System.out.print("Enter the name of the doctor: ");
        s.skip("((?<!\\R)\\s)*");
        String name = s.nextLine();
        name = WordUtils.capitalize(name);

        System.out.print("Enter the age of the doctor: ");
        int age = s.nextInt();

        System.out.println("Enter the speciality of the doctor: \n");

        ArrayList<String> allSpecialities = Specialities.getAllSpecialities();
        Specialities.printSpec(allSpecialities);

        System.out.print("\n\nYour number choice: ");
        int specialty = s.nextInt();

        System.out.print("Enter the degree of the doctor: ");
        s.skip("((?<!\\R)\\s)*");
        String degree = s.nextLine();

        Doctor doctor = new Doctor(name, age, specialty, degree);
        DoctorDB.insertNewDoctor(doctor);

        System.out.println("Dr." + name + " is added successfully.");
    }

    public static void deleteDoctorInSystem() throws Exception {
        System.out.print("Enter the name of the doctor: ");
        s.skip("\n");
        String name = s.nextLine();
        name = WordUtils.capitalize(name);

        Doctor doctor = new Doctor(name);
        DoctorDB.deleteExistedDoctor(doctor);
        System.out.println("Dr. " + name + " deleted successfully.");
    }

}
