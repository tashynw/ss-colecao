import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.nio.channels.NetworkChannel;


public class ManageCustomer {
    public ManageCustomer(){}

    public static Customer findCustomer(String firstName, String lastName){
        Customer customer = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Customers WHERE firstName='%s' AND lastName='%s';", firstName, lastName));

            while(rs.next()){
                customer = new Customer(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("email")
                );
            }
            System.out.println("RESULT "+customer);
            return customer;
        } catch(Error | SQLException | ClassNotFoundException e){

            System.out.println("DB ERROR findCustomer()"+e);
            return customer;
        }
    }

    public static Customer findCustomerById(String id){
        Customer customer = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Customers WHERE customer_id='%s';", id));

            while(rs.next()){
                customer = new Customer(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email")
                );
            }
            System.out.println("RESULT "+customer);
            return customer;
        } catch(Error | SQLException | ClassNotFoundException e){

            System.out.println("DB ERROR findCustomerById()"+e);
            return customer;
        }
    }

    public static List<Customer> getAllCustomers(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Customers;");
            List<Customer> customerList = new ArrayList<>();
            while(rs.next()){
                customerList.add(
                    new Customer(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email")
                    )
                );
            }
            System.out.println("RESULT "+customerList);
            return customerList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR getAllCustomers()"+e);
            return new ArrayList<>();
        }
    }

    public static boolean createCustomer(Customer newCustomer){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(String.format("INSERT INTO Customers (firstName, lastName, address, phone_number, email) VALUES ('%s','%s','%s','%s','%s')", newCustomer.getFName(), newCustomer.getLName(), newCustomer.getAddress(), newCustomer.getContact(), newCustomer.getEmail()));
            System.out.println("TEST");
            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR createCustomer()"+e);
            return false;
        }
    }

    public static String getIdFromCustomerName(String firstName, String lastName){
        String id = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT customer_id from Customers WHERE firstName='%s' AND lastName='%s';", firstName, lastName));

            if (rs.next()) {
                id = rs.getString("customer_id");
            }

            System.out.println("RESULT getIdFromCustomerName" + id);
            return id;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR getIdFromCustomerName getIdFromCustomerName()"+e);
            return id;
        }
    }

    public static List<Customer> searchCustomerFromFirstName(String firstName){
        String id = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Customers WHERE firstName LIKE '%%%s%%';", firstName));
            List<Customer> customerList = new ArrayList<>();
            while(rs.next()){
                customerList.add(
                        new Customer(
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("phone_number"),
                                rs.getString("address"),
                                rs.getString("email")
                        )
                );
            }
            System.out.println("RESULT "+customerList);
            return customerList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR searchCustomerFromFirstName()"+e);
            return new ArrayList<>();
        }
    }

}
