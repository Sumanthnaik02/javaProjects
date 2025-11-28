package project1_employee_management;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");
            int choice =sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter Name: ");
                    String name = sc.next();
                    System.out.println("Enter Department: ");
                    String department = sc.next();
                    System.out.println("Enter Salary: ");
                    double salary = sc.nextDouble();
                    System.out.println("Enter Email: ");
                    String email = sc.next();
                    System.out.println("Enter phone: ");
                    String phone = sc.next();
                    EmployeeDAO.addEmployees(new Employee(0,name,department,salary,email,phone));
                    break;

                case 2:
                        EmployeeDAO.viewEmployees();
                    break;

                case 3:
                    System.out.println("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    System.out.println("Enter the New Salary: ");
                    double newSalary = sc.nextDouble();
                    EmployeeDAO.updateEmployee(empId,newSalary);
                    break;

                case 4:
                    System.out.println("Enter Employee ID to Delete: ");
                    int empid = sc.nextInt();
                    EmployeeDAO.deleteEmployee(empid);
                    break;

                case 5:
                    System.out.println("Exiting........");
                    sc.close();
                    return;
            }
        }
    }
}
