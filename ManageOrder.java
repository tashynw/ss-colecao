import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageOrder {
    public ManageOrder(){}

    public static boolean createOrder(Order newOrder, String stockId){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(String.format("INSERT INTO Orders (customer_id, stock_id, item_count, deliver_mode, status, totalprice) VALUES ('%s','%s','%s','%s','%s', '%s')", ManageCustomer.getIdFromCustomerName(newOrder.getCustomerFirstName(), newOrder.getCustomerLastName()), stockId, newOrder.getItemCount(), newOrder.getMode(), newOrder.getStatus(), newOrder.getTotalPrice()));
            EmailService.sendOrderCreatedEmail(newOrder);
            AdministerInvoice.createInvoice(newOrder);
            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return false;
        }
    }

    public static List<Object[]> getAllOrders(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Orders;");
            List<Object[]> orderList = new ArrayList<>();
            while (rs.next()) {
                String orderId = String.valueOf(rs.getInt("order_id"));
                Customer customer = ManageCustomer.findCustomerById(String.valueOf(rs.getInt("customer_id")));
                Stock stock = ManageStock.findStockFromId(rs.getString("stock_id"));
                int itemCount = rs.getInt("item_count");
                String deliverMode = rs.getString("deliver_mode");
                String status = rs.getString("status");
                Double totalPrice = Double.valueOf(rs.getFloat("totalprice"));

                Order order = new Order(customer, itemCount, deliverMode, status, stock, totalPrice);
                orderList.add(new Object[]{ orderId,order });
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
            rs = stmt.executeQuery(String.format("SELECT * from Orders WHERE order_id='%s';", id));

            if (rs.next()) {
                Customer customer = ManageCustomer.findCustomerById(rs.getString("customer_id"));
                Stock stock = ManageStock.findStock(rs.getString("stock_id"));
                int itemCount = rs.getInt("item_count");
                String deliveryMode = rs.getString("deliver_mode");
                String status = rs.getString("status");
                Double totalPrice = Double.valueOf(rs.getFloat("totalprice"));
                order = new Order(customer, itemCount, deliveryMode, status, stock, totalPrice);
            }

            System.out.println("RESULT " + order);
            return order;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return order;
        }
    }
}
