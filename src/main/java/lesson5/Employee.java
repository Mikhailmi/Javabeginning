package lesson5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;


    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }



    public void info() {
        System.out.println("Full Name " + fullName + " Position " + position + " Email " + email + " Phone number " + phoneNumber + " Salary " + salary + " Age " + age);
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee("Petrov Petr", "director", "P.Petrov@mail.ru", "86669996699", 100000, 43);
        Employee employee2 = new Employee("Ivanov Ivan", "deputy director", "I.Ivanov@mail.ru", "89996669966", 85000, 41);
        Employee employee3 = new Employee("Ivanov Petr", "chief specialist", "P.Ivanov@mail.ru", "89998889966", 70000, 39);
        Employee employee4 = new Employee("Petrov Ivan", "leader specialist", "I.Petrov@mail.ru", "89989996699", 60000, 34);
        Employee employee5 = new Employee("Sidorov Ivan", "specialist", "I.Sidorov@mail.ru", "89966669966", 50000, 22);


        if (employee1.getAge() > 40) employee1.info();
        if (employee2.getAge() > 40) employee2.info();
        if (employee3.getAge() > 40) employee3.info();
        if (employee4.getAge() > 40) employee4.info();
        if (employee5.getAge() > 40) employee5.info();

    }

    public int getAge() {
        return age;

    }
}