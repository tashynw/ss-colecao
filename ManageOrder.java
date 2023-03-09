import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageOrder {
    public ManageOrder(){}

    public static boolean createOrder(Order newOrder, String stockId){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("INSERT INTO Orders (customer_id, stock_id, item_count, deliver_mode, status) VALUES (%s,%s,%s,%s,%s)", newOrder.getCustomerID(), stockId, newOrder.getItemCount(), newOrder.getMode(), newOrder.getStatus()));

            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return false;
        }
    }

    public static List<Order> getAllOrders(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Orders;");
            List<Order> orderList = new ArrayList<>();
            while (rs.next()) {
                Customer customer = ManageCustomer.findCustomer(String.valueOf(rs.getInt("customer_id")));
                Stock stock = null;
                int itemCount = rs.getInt("item_count");
                String deliverMode = rs.getString("deliver_mode");
                String status = rs.getString("status");

                Order order = new Order(customer, itemCount, deliverMode, status, stock);
                orderList.add(order);
            }
            System.out.println("RESULT "+orderList);
            return orderList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return new ArrayList<>();
        }
    }

    public static Order findOrder(String id){
        Order order = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Orders WHERE order_id="+id+";");

            if (rs.next()) {
                Customer customer = ManageCustomer.findCustomer(rs.getString("customer_id"));
                Stock stock = ManageStock.findStock(rs.getString("stock_id"));
                int itemCount = rs.getInt("item_count");
                String deliveryMode = rs.getString("deliver_mode");
                String status = rs.getString("status");
                order = new Order(customer, itemCount, deliveryMode, status, stock);
            }

            System.out.println("RESULT " + order);
            return order;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return order;
        }
    }
}
