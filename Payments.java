// This java file has getters and setters for all the attributes in Payment 
public class Payments{
    
    private int pay_id;
    private String pay_date;
    private String start_date;
    private String end_date;
    private String claim_date;
    private float paymentAmount;
    private int w_id;
    private String work_type;
    //gets the payment id. 
    public int getPay_Id(){
        return pay_id;
    }
    // gets the pay date for a particular Payment.
    public String getPay_date(){
        return pay_date;
    }
    // gets the pay start date for the payment.
    public String getStart_date(){
        return start_date;
    }
    // gets the pay end date for the payment.
    public String getEnd_date(){
        return end_date;
    }
    // gets the pay claim date for the payment.
    public String getClaim_date(){
        return claim_date;
    }
    // gets the payment Amount .
    public float getPaymentAmount(){
        return paymentAmount;
    }
    // gets the Writer id for whom the payment is issued.
    public int getW_id(){
        return w_id;
    }
    // Gets the work type of the Writer 
    public String getWork_type(){
        return work_type;
    }
    // Sets the payment id. 
    public void setPay_Id(int pay_id){
        this.pay_id = pay_id;
    }
    // Sets the payment Date for the payment 
    public void setPay_date(String pay_date){
        this.pay_date = pay_date;
    }
    // Sets the Start date of the payment 
    public void setStart_date(String start_date){
        this.start_date = start_date;
   
    }
     // Sets the end  date of the payment;
    public void setEnd_date(String end_date){
        this.end_date = end_date;
    }
    // Sets the Claim date set for a payment issued. 
    public void setClaim_date(String claim_date){
        this.claim_date = claim_date;
    }
    // Sets the Payment Amount for the Payment issued 
    public void setPaymentAmount(float paymentAmount){
        this.paymentAmount = paymentAmount ;
    }
    // Sets the Writer id for the payment   
    public void setW_id(int w_id){
        this.w_id = w_id;
    }
    // Sets the Work type of the writer. 
    public void setWork_type(String work_type){
        this.work_type = work_type;
    }

}