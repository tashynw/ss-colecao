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
    public ManageCustomer(){

    }

    //Function to search for a customer
    /*public Customer findCustomer(String iD){
        Customer cust = null;
        int j = 0;
        while(clst.size()>j){
            cust = clst.get(j);
            if(cust.getID().equals(iD))
                return cust;
            j++;
            cust = null;
        }   
        return cust;
    }*/
    
    //Function to create an array list of customers from a file
    //(String fName, String lName, int id, int contact, String address, String email)
    //620148438, Tiffany, Parkinson, 8763409204, tiffanyparkinson18@gmail.com, 101 Wallpark Street
    public ArrayList<Customer> load_customers(String cfile){
        Scanner sscan = null;
        ArrayList<Customer> clist = new ArrayList<Customer>();
        
        try{
            sscan  = new Scanner(new File(cfile));
            while(sscan.hasNext())
            {
                String [] nextLine = sscan.nextLine().split(", ");

                String id = nextLine[0];
                String fname = nextLine[1];
                String lname = nextLine[2];
                String contact = nextLine[3];
                String email = nextLine[4];
                String add = nextLine[5];
                
                //(fname, lname, id, contact, add, email)
                Customer cust = new Customer(fname, lname, id, contact, add, email);
                System.out.println(id);
                clist.add(cust);
            }
            sscan.close();
        }
        catch(IOException e)
        {}
        return clist; 
    }

    public static Customer findCustomer(String id){
        Customer customer = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Customers WHERE customer_id="+id+";");

            while(rs.next()){
                customer = new Customer(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("customer_id"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getString("email")
                );
            }
            System.out.println("RESULT "+customer);
            return customer;
        } catch(Error | SQLException | ClassNotFoundException e){

            System.out.println("DB ERROR "+e);
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
                customerList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(0), rs.getString(4), rs.getString(3), rs.getString(5)));
            }
            System.out.println("RESULT "+customerList);
            return customerList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return new ArrayList<>();
        }
    }

    public static boolean createCustomer(Customer newCustomer){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("INSERT INTO Customers (firstName, lastName, address, phone_number, email) VALUES (%s,%s,%s,%s,%s)", newCustomer.getFName(), newCustomer.getLName(), newCustomer.getAddress(), newCustomer.getContact(), newCustomer.getEmail()));

            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return false;
        }
    }

}
