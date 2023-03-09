import java.util.List;

public class Order {
    //attributes
    private Customer customer;
    private int item_count;
    private String deliveryMode;
    private Stock order_detail;
    private String status;

    //Constructor
    public Order(Customer customer, int item_count, String delMode, String status, Stock details){
        this.customer = customer;
        this.item_count = item_count;
        this.status = status;
        this.deliveryMode = delMode;
        this.order_detail = details;
    }

    public String getOwner(){
        return customer.getFullName();
    }

    public String getCustomerID(){
        return this.customer.getID();
    }
    public Stock getItem(){
        return this.order_detail;
    }
    public String getCustomerName(){
        return customer.getFullName();
    }

    public int getItemCount(){
        return this.item_count;
    }

    public String getMode(){
        return this.deliveryMode;
    }

    public String getStatus(){
        return this.status;
    }
}
