 // This java file has getters and setters for all the attributes in Publication
public class Publication{
    int p_id;
    String title;
    String type;
    String topic;
    String p_date;
    // gets the publicaton id for an new Article publication 
   
    public int getP_id(){
        return p_id;
    }
    // Gets the Title of the pubvlication. 
    public String getTitle(){
        return title;
    }
    // Gets the type of the publication. 
    public String getType(){
        return type;
    }
    // Grts the Topic of the Publication 
    public String getTopic(){
        return topic;
    }
    // Gets the Topic for the publication
    public String getP_date(){
        return p_date;
    }
      // sets the publicaton id for a new Article publication.
    public void setP_id(int p_id){
        this.p_id = p_id;
    }
    // Sets the tittle for the Publication 
    public void setTitle(String title){
        this.title = title;
    }
    // Sets the Type for the Publication
    public void setType(String type){
        this.type = type;
    }
    // Sets the Topic for the particular Publication
    public void setTopic(String topic){
        this.topic = topic;
    }
      // sets the publicaton Date 
    public void setP_date(String p_date){
        this.p_date = p_date;
    }



}