public class Book{

    // This java file has getters and setters for all the attributes for Book
    int p_id;
    int edition;
    String isbn;
    // gets the publicaton id for a book publication 
    public int getP_id(){
        return p_id;
    }
    // gets the edition for a book publication 

    public int getEdition(){
        return edition;
    }
    // gets the isbn for a book publication 
    public String getIsbn(){
        return isbn;
    }
    // sets the publicaton id for a book publication 
    public void setP_id(int p_id){
        this.p_id = p_id;
    }

   // sets the edition for a book publication 
    public void setEdition(int edition){
        this.edition = edition;
    }
   // sets the isbn for a book publication 
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
}