package resources.main;

public class Human {

    private int id;
    private String name;
    private int age;

    public Human(String name, int age) {
        setIdRandomly();
        setName(name);
        setAge(age);
    }

    public Human(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setIdRandomly() {
        double doubleRandomNumber = Math.random() * 10000000;
        this.id = (int) doubleRandomNumber;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.length() < 3) {
            System.out.println("Enter correct name!");
        } else {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 0 || age > 100) {
            System.out.println("Enter the correct age!");
        } else {
            this.age = age;
        }
    }
}
