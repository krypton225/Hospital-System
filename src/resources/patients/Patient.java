package resources.patients;

import resources.main.Human;

public class Patient extends Human {

    private int specialityPatient;
    private byte status;

    public Patient(String name, byte age, int specialityPatient, byte status) {
        super(name, age);
        setSpecialityPatient(specialityPatient);
        setStatus(status);
    }

    public Patient(String name) {
        super(name);
    }

    public int getSpecialityPatient() {
        return specialityPatient;
    }

    private void setSpecialityPatient(int specialityPatient) {
        if (specialityPatient <= 50) {
            this.specialityPatient = specialityPatient;
        } else {
            System.out.println("Error with");
        }
    }

    public byte getStatus() {
        return status;
    }

    private void setStatus(byte status) {
        this.status = status;
    }

}
