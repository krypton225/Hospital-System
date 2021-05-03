package resources.doctor;

import resources.main.Human;

public class Doctor extends Human {

    private int specialty;
    private String degree;

    public Doctor(String name) {
        super(name);
    }

    public Doctor(String name, int age, int specialty, String degree) {
        super(name, age);
        setSpecialty(specialty);
        setDegree(degree);
    }

    public int getSpecialty() {
        return specialty;
    }

    private void setSpecialty(int specialty) {
        this.specialty = specialty;
    }

    public String getDegree() {
        return degree;
    }

    private void setDegree(String degree) {
        this.degree = degree;
    }

}
