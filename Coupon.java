public class Coupon {
    //Attributes
    private Order order;
    private Double couponPercentage;

    //Constructors
    public Coupon(Order order, Double percentage){
        this.order = order;
        this.couponPercentage = percentage;
    }

    public Order getOrder(){
        return this.order;
    }

    public Double getPercentage(){
        return this.couponPercentage;
    }
}
