 // This java file has getters and setters for all the attributes in Order
public class Order{
   
    private int o_id;
    private String type;
    private int copies;
    private float price;
    private float shipping_cost;
    private String due_date;
    private String order_date;
    private int p_id;
    private int d_id;
    // gets the order id for an Order. 
    public int getO_id(){
        return o_id;
    }
    // gets the order type for an Order. 
    public String getType(){
        return type;
    }
    // gets the number of copies for an Order. 
    public int getCopies(){
        return copies;
    }
    // gets the order price for an Order. 
    public float getPrice(){
        return price;
    }
    // gets the Shipping cost for an Order. 
    public float getShipping_cost(){
        return shipping_cost;
    }
    // gets the due date for an Order. 

    public String getDue_date(){
        return due_date;
    }
    // gets the Order date for an Order.
    public String getOrder_date(){
        return order_date;
    }
    // gets the Publication id needed in an Order.
    public int getP_id(){
        return p_id;
    }
    // gets the Distributor id for a Particular Order.
    public int getD_id(){
        return d_id;
    }
   //sets the order id.
    public void setO_id(int o_id){
        this.o_id = o_id ;
    }
    //setsb the type of the order placed.
    public void setType(String type){
        this.type = type;
    }
    // Sets the number of copies needed for a publication
    public void setCopies(int copies){
        this.copies = copies ;
    }
    // Sets the Price od the order placed
    public void setPrice(float price){
        this.price = price ;
    }
    // Sets the Shipping cost for the order
    public void setShipping_cost(float shipping_cost){
        this.shipping_cost = shipping_cost ;
    }
    // Sets the date due for the order to get completed. 
    public void setDue_date(String due_date){
        this.due_date = due_date;
    }
    // Sets the date the order is placed on. 
    public void setOrder_date(String order_date){
        this.order_date = order_date ;
    }
    // sets the publication id for the order

    public void setP_id(int p_id){
        this.p_id = p_id ;
    }
    // Sets the distrubutor id for the order placed. 
    public void setD_id(int d_id){
        this.d_id = d_id ;
    }
}
