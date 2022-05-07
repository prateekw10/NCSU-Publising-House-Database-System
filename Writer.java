 // This java file has getters and setters for all the attributes in Writer 
public class Writer{
    private int w_id;
    private String w_name;
    private String type;
    // Gets the writrer id. 
    public int getW_id(){
        return w_id;
    }
    //  gets the Writer name.
    public String getW_name(){
        return w_name;
    }
    // Gets the Type of the Writer 
    public String getType(){
        return type;
    }
    //Gets the Writer id 
    public void setW_id(int w_id){
        this.w_id = w_id;
    }
    // Gets the Writer name 
    public void setW_name(String w_name){
        this.w_name = w_name;
    }
      // sets the Type for a Writer. 
    public void setType(String type){
        this.type = type;
    }
}