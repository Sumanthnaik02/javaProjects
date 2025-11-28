package project1_employee_management;

public class Employee {
    private int empId;
    private String name;
    private String department;
    private double salary;
    private String email;
    private String phone;

    public Employee(int empId,
                    String name,
                    String department,
                    double salary,
                    String email,
                    String phone){
        this.empId=empId;
        this.name=name;
        this.department=department;
        this.salary=salary;
        this.email=email;
        this.phone=phone;
    }
    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}