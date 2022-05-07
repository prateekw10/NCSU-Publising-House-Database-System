// This java file has getters and setters for all the attributes in Chapter  
public class Chapter{
   
    int p_id;
    int number;
    String title;
    String chapter_date;
    // gets the publicaton id for a Chapter 
    public int getP_id(){
        return p_id;
    }
    // gets the Chapter number  for a Chapter 
    public int getNumber(){
        return number;
    }
    // gets the Title for a Chapter 
    public String getTitle(){
        return title;
    }
    // gets the date for a Chapter 
    public String getdate(){
        return chapter_date;
    }
    // sets the publicaton id for a Chapter 
    public void setP_id(int p_id){
        this.p_id = p_id;
    }

    // sets the Chapter number  for a Chapter 
    public void setNumber(int number){
        this.number = number;
    }
    // sets the Chapter title for a Chapter 
    public void setTitle(String title){
        this.title = title;
    }
    // sets the Chapter date for a Chapter 

    public void setDate(String chapter_date){
        this.chapter_date = chapter_date;
    }
}