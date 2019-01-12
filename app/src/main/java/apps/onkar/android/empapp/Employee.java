package apps.onkar.android.empapp;

public class Employee {
    public String age;

    public Employee(){
    }

    public Employee(String name, String age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
