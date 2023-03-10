import java.sql.*;

public class ManageStock {
    public ManageStock(){}

    public static boolean createStock(Stock newStock){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(String.format("INSERT INTO Stocks (item_name, item_color, item_size, price) VALUES ('%s','%s','%s','%s')", newStock.getItemName(), newStock.getItemColor(), newStock.getItemSize(), newStock.getPrice()));

            return true;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return false;
        }
    }

    public static Stock findStock(String stock_name){
        Stock stock = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Stocks WHERE item_name='%s'", stock_name));

            if (rs.next()) {
                StockType stockType = StockType.valueOf(rs.getString("item_name"));
                ItemColor itemColor = ItemColor.valueOf(rs.getString("item_color"));
                Size itemSize = Size.valueOf(rs.getString("item_size"));
                int price = rs.getInt("price");
                stock = new Stock(stockType, itemColor, itemSize, price);
            }

            System.out.println("RESULT " + stock);
            return stock;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return stock;
        }
    }

    public static Stock findStockFromId(String stock_id){
        Stock stock = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Stocks WHERE stock_id='%s'", stock_id));

            if (rs.next()) {
                StockType stockType = StockType.valueOf(rs.getString("item_name"));
                ItemColor itemColor = ItemColor.valueOf(rs.getString("item_color"));
                Size itemSize = Size.valueOf(rs.getString("item_size"));
                int price = rs.getInt("price");
                stock = new Stock(stockType, itemColor, itemSize, price);
            }

            System.out.println("RESULT " + stock);
            return stock;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return stock;
        }
    }

    public static String getIdFromItemName(String itemName){
        String id = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT stock_id from Stocks WHERE item_name='%s'", itemName));

            if (rs.next()) {
                id = rs.getString("stock_id");
            }

            System.out.println("RESULT " + id);
            return id;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return id;
        }
    }

    public static String getPriceFromItemName(String itemName){
        String price = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Stocks WHERE item_name='%s';", itemName));

            if (rs.next()) {
                price = rs.getString("price");
            }

            System.out.println("RESULT " + price);
            return price;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return price;
        }
    }
}
