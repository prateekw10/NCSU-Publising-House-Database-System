
// This java file has getters and setters for all the attributes in  Articles 
public class Articles{  

    public int p_id;
    public int article_no;
    public String article_title;
    public String text;
    public String article_date;
    // gets the publicaton id for an new Article publication 
    public int getP_id(){
        return p_id;
    }
    // gets the article number for an Article publication 
    public int getArticle_no(){
        return article_no;
    }
    // gets the article title for an Article publication 
    public String getArticle_title(){
        return article_title;
    }
    // gets the article text for an Article publication 
    public String getText(){
        return text;
    }
    // gets the article date for an Article publication 
    public String getArticle_date(){
        return article_date;
    }
   
     // sets the publicaton id for a new Article publication 
    public void setP_id(int p_id){
        this.p_id = p_id;
    }
    // sets the Article number  for a new Article publication 
    public void setArticle_no(int article_no){
        this.article_no = article_no;
    }
    // sets the Article tittle for a new Article publication 
    public void setArticle_title(String article_title){
        this.article_title = article_title;
    }
   // sets the Article Text for a new Article publication 
    public void setText(String text){
        this.text = text;
    }
    // sets the Article date for a new Article publication 
    public void setArticle_date(String article_date){
        this.article_date = article_date;
    }



}
