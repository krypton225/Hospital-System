package resources.patients;

import org.apache.commons.lang3.text.WordUtils;
import resources.main.Specialities;

import java.util.ArrayList;
import java.util.Scanner;

import static resources.patients.PatientDB.deleteExistedPatient;

public class PatientOperations {

    private final static Scanner s = new Scanner(System.in);
    private static ArrayList<String> list = new ArrayList<>();

    public static void addPatientToListAndDB() throws Exception {
        Patient patient = null;

        System.out.print("Enter name of patient: ");
        s.skip("((?<!\\R)\\s)*");
        String namePatient = s.nextLine();
        namePatient = WordUtils.capitalize(namePatient);

        System.out.print("Enter age of patient: ");
        byte agePatient = s.nextByte();

        System.out.println("Enter speciality of patient: ");

        ArrayList<String> allSpecialities = Specialities.getAllSpecialities();
        Specialities.printSpec(allSpecialities);

        System.out.print("\n\nYour number choice: ");
        int specialityPatient;
        while (true) {
            specialityPatient = s.nextInt();
            if (specialityPatient >= 1 && specialityPatient <= 20) {
                break;
            }
            System.out.println("Wrong input of speciality of patient!\nTry again!");
            System.out.print("\nYour number choice: ");
        }

        System.out.println("Enter status of patient (0 for regular, 1 for urgent)");
        byte statusPatient;
        while (true) {
            statusPatient = s.nextByte();
            if (statusPatient == 0 || statusPatient == 1) {
                break;
            }
            System.out.println("Wrong input of status!\nTry again!");
            System.out.println("Enter status of patient (0 for regular, 1 for urgent)");
        }

        patient = new Patient(namePatient, agePatient, specialityPatient, statusPatient);
        PatientDB.insertNewPatient(patient);
        System.out.println("\n\nMr/Ms " + namePatient + " is added successfully.\n\n");

        if (patient.getStatus() == 1) {
            list.add(0, patient.getName());
        } else {
            list.add(patient.getName());
        }
    }

    public static void printAllPatientsOfToday() {
        System.out.println("\n\nThis is all patients for this moment: ");
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            System.out.println(j + "- " + list.get(i));
        }
    }

    public static void deletePatientFromListAndDB() throws Exception {
        System.out.print("Enter name of patient: ");
        s.skip("((?<!\\R)\\s)*");
        String namePatient = s.nextLine();
        namePatient = WordUtils.capitalize(namePatient);

        Patient patient = new Patient(namePatient);

        deleteExistedPatient(patient);
//        list.remove(patient.getName());
        list.remove(0);
        System.out.println("Mr/Ms: " + namePatient + " is deleted successfully.");
    }

    public static void getNextPatient() {
        if (list.isEmpty()) {
            System.out.println("There is no patients.");
        } else {
            System.out.print("so, next patient is: ");
            System.out.println(list);
        }
    }

    public static void addThePastPatientsInList(String nameOfPatient) {
        if (!list.contains(nameOfPatient)) {
            list.add(nameOfPatient);
        }
    }
}
