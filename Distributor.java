// This java file has getters and setters for all the attributes in Distributor 
public class Distributor{
   

    public int d_id;
    public String city;
    public String address;
    public String type;
    public String d_name;
    public float balance;
    public String phone ; 
    public String contact_person ; 
    //gets the Distributor id.
    public int getD_id(){
        return d_id;
    }
   //gets the Distributor city.
    public String getCity(){
        return city;
    }
    //gets the Distributor address.
    public String getAddress(){
        return address;
    }
    //gets the Distributor type.
    public String getType(){
        return type;
    }
    //gets the Distributor name.
    public String getD_name(){
        return d_name;
    }
    //gets the Distributor Balance.
    public float getBalance(){
        return balance ; 
    }
    //gets the Distributor contact information.
    public String getPhone(){
        return phone ;
    }
    //gets the contact person for a Distributor
    public String getContact_person(){
        return contact_person ;
    }
    // sets the distributor id for a new distributor
    public void setD_id(int d_id){
        this.d_id = d_id;
    }
    // sets the distributor cityfor a new distributor
    public void setCity(String city){
        this.city = city;
    }
    // sets the distributor address for a new distributor
    public void setAddress(String address){
        this.address = address;
    }
    // sets the distributor type for a new distributor
    public void setType(String type){
        this.type = type;
    }
    // sets the distributor Name for a new distributor
    public void setD_name(String d_name){
        this.d_name = d_name;
    }
    // sets the distributor Balance.
    public void setBalance(float balance){
        this.balance = balance ;
    }
    // sets the distributor phone.
    public void setPhone(String phone){
        this.phone = phone;
    }
   // sets the distributor Name for a new distributor
    public void setContact_person(String contact_person){
        this.contact_person = contact_person;
    }



}
