import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageCoupon {
    public ManageCoupon(){}

    public static boolean createCouponAndAddToOrder(String orderId,Order order, Double percentage){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();

            //create coupon in sql
            int rowsAffectedByCreation = stmt.executeUpdate(String.format("INSERT INTO Coupons (order_id, coupon_percentage) VALUES ('%s','%s')", orderId, percentage));

            //modify order's price respectively
            int rowsAffectedByUpdate = stmt.executeUpdate(String.format("UPDATE Orders SET totalprice=totalprice*%f WHERE order_id='%s'", percentage, orderId));
            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR createCouponAndAddToOrder()"+e);
            return false;
        }
    }
}
