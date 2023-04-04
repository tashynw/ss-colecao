public class Stock{
    //Attributes
    private double price;
    private String itemName;
    private String s_color;
    private String s_size;

    //Constructor
    public Stock(String itemName, String stockColor, String stockSize, double price){
        this.itemName = itemName;
        this.s_color = stockColor;
        this.s_size = stockSize;
        this.price = price;
    }

    //Method to return the type of stock
    public String getStockName(){
        return this.itemName;
    }

    //Method to return the color of an item
    public String getItemColor(){
        return this.s_color;
    }

    //Method to return the size of an item
    public String getItemSize(){
        return s_size;
    }
    public String getItemName(){ return this.itemName; }
    public double getPrice(){
        return price;
    }
}