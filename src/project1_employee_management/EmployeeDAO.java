package project1_employee_management;

import java.sql.*;

public class EmployeeDAO {

    //insert operation
    public static void addEmployees(Employee emp){
        String sql = "INSERT INTO Employees(name,department,salary,email,phone) VALUES(?,?,?,?,?)";

        try(Connection connect = Databaseconnection.connect();
            PreparedStatement stmt = connect.prepareStatement(sql)){

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getDepartment());
            stmt.setDouble(3,emp.getSalary());
            stmt.setString(4,emp.getEmail());;
            stmt.setString(5,emp.getPhone());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted>0){
                System.out.println("Employee added successfully!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //view operation
    public static void viewEmployees() {
        String sql = "SELECT * FROM Employees";

        try (Connection connect = Databaseconnection.connect();
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("emp_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("department") + " | " +
                        rs.getDouble("salary") + " | " +
                        rs.getString("email") + " | " +
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //update operation
    public static void updateEmployee(int empId,double newSalary){
            String sql = "UPDATE Employees SET salary= ? WHERE emp_id=?";

            try(Connection connect = Databaseconnection.connect();
                PreparedStatement stmt = connect.prepareStatement(sql)){

                stmt.setDouble(1,newSalary);
                stmt.setInt(2,empId);

                int rowsUpdated = stmt.executeUpdate();
                if(rowsUpdated>0){
                    System.out.println("Employee salary updated successfully!");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    //delete operation
    public static void deleteEmployee(int empId){
        String sql = "DELETE FROM Employees WHERE emp_id=?";

        try(Connection connect = Databaseconnection.connect();
            PreparedStatement stmt = connect.prepareStatement(sql)){

            stmt.setInt(1,empId);

            int rowsDeleted = stmt.executeUpdate();
            if(rowsDeleted>0){
                System.out.println("Employee deleted successfully!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
