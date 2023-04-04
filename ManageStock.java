import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean deleteStock(String stockId){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(String.format("DELETE FROM Stocks WHERE stock_id=%s;", stockId));

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
                String stockName = String.valueOf(rs.getString("item_name"));
                String itemColor = String.valueOf(rs.getString("item_color"));
                String itemSize = String.valueOf(rs.getString("item_size"));
                int price = rs.getInt("price");
                stock = new Stock(stockName, itemColor, itemSize, price);
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
                String stockName = String.valueOf(rs.getString("item_name"));
                String itemColor = String.valueOf(rs.getString("item_color"));
                String itemSize = String.valueOf(rs.getString("item_size"));
                int price = rs.getInt("price");
                stock = new Stock(stockName, itemColor, itemSize, price);
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
    public static List<Object[]> getAllStocks(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from Stocks;");
            List<Object[]> stockList = new ArrayList<>();
            while (rs.next()) {
                String stockId = String.valueOf(rs.getInt("stock_id"));
                String itemName = rs.getString("item_name");
                String itemColor = rs.getString("item_color");
                String itemSize = rs.getString("item_size");
                Double price = Double.valueOf(rs.getFloat("price"));

                Stock stock = new Stock(itemName, itemColor,itemSize,price);
                stockList.add(new Object[]{ stockId,stock });
            }
            System.out.println("RESULT "+stockList);
            return stockList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return new ArrayList<>();
        }
    }
    public static List<Object[]> searchStocksByItemName(String itemNameToSearch){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ss_colecao?" + "user=root&password=password!23");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * from Stocks WHERE item_name LIKE '%%%s%%';", itemNameToSearch));
            List<Object[]> stockList = new ArrayList<>();
            while (rs.next()) {
                String stockId = String.valueOf(rs.getInt("stock_id"));
                String itemName = rs.getString("item_name");
                String itemColor = rs.getString("item_color");
                String itemSize = rs.getString("item_size");
                Double price = Double.valueOf(rs.getFloat("price"));

                Stock stock = new Stock(itemName, itemColor,itemSize,price);
                stockList.add(new Object[]{ stockId,stock });
            }
            System.out.println("RESULT "+stockList);
            return stockList;
        } catch(Error | SQLException | ClassNotFoundException e){
            System.out.println("DB ERROR "+e);
            return new ArrayList<>();
        }
    }
}
