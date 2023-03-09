public class Stock{
    //Attributes
    private int price;
    private String itemName;
    private StockType s_type;
    private ItemColor s_color;
    private Size s_size;

    //Constructor
    public Stock(StockType st, ItemColor sc, Size s, int price){
        this.s_type = st;
        this.s_color = sc;
        this.s_size = s;
        this.price = price;
    }

    //Method to return the type of stock
    public StockType getStockType(){
        return s_type;
    }

    //Method to return the color of an item
    public ItemColor getItemColor(){
        return s_color;
    }

    //Method to return the size of an item
    public Size getItemSize(){
        return s_size;
    }
    public String getItemName(){ return this.itemName; }
    public int getPrice(){
        return price;
    }
}