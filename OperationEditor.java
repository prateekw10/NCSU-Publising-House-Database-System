import java.sql.*;
import java.util.Scanner;

public class OperationEditor {
    
    static void insertPublication(Scanner sc, Statement stmt) {
       Publication p = new Publication();
        System.out.print("Enter Publication Title: ");
        p.setTitle(sc.nextLine());
        System.out.print("Enter Publication Type: ");
        p.setType(sc.nextLine());
        System.out.print("Enter Publication Topic: ");
        p.setTopic(sc.nextLine());
        
        System.out.print("Enter Publication Date in the format yyyy-mm-dd: ");
        p.setP_date(sc.nextLine());
            
        
        String insertQuery = "INSERT INTO Publication (title,type,topic,p_date) Values('";
        insertQuery += p.getTitle() + "','" + p.getType() + "','" + p.getTopic() + "','";
        insertQuery += p.getP_date()  + "')"  ;

        //System.out.println("Query: "+insertQuery);
        try {
            stmt.executeUpdate(insertQuery);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(p.getType().equalsIgnoreCase("Book")){
            Book b = new Book();
            Chapter c = new Chapter();
            System.out.print("Enter Book Edition: ");
        b.setEdition(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Book ISBN: ");
        b.setIsbn(sc.nextLine());
        System.out.print("Enter Chapter number: ");
        c.setNumber(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Chapter title: ");
        c.setTitle(sc.nextLine());
        System.out.print("Enter Chapter date in the format yyyy-mm-dd: ");
        c.setDate(sc.nextLine());
        String insertQuery2 = "INSERT INTO Book (p_id,edition,ISBN) select p_id , ";
        insertQuery2 += b.getEdition() + ",'" + b.getIsbn() + "'";
        insertQuery2 += " from Publication where p_id not in (";
        insertQuery2 += "select p_id from Book UNION select p_id from Magazine_Journal)";

        
        try {
            stmt.executeUpdate(insertQuery2);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        String insertQuery3 = "INSERT INTO Chapters (chapter_no, p_id, chapter_title, chapter_date) select ";
        insertQuery3 += c.getNumber() + ", p_id , '" + c.getTitle() + "','" + c.getdate() + "'";
        insertQuery3 += " from Book where p_id not in (";
        insertQuery3 += "select p_id from Chapters)";
        try {
            stmt.executeUpdate(insertQuery3);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        }

        else{
            Magazine_Journal m = new Magazine_Journal();
       
        Articles a = new Articles();
        System.out.print("Enter Issue: ");
        m.setIssue(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Periodicity: ");
        m.setPeriodicity(sc.nextLine());
        System.out.print("Enter Article number: ");
        a.setArticle_no(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Article title: ");
        a.setArticle_title(sc.nextLine());
        System.out.print("Enter Article text: ");
        a.setText(sc.nextLine());
        System.out.print("Enter Article date in the format yyyy-mm-dd: ");
        
        a.setArticle_date(sc.nextLine());
       
        String insertQuery2 = "INSERT INTO Magazine_Journal (p_id,issue,periodicity) select ";
        insertQuery2 += "p_id , " + m.getIssue() + ",'" + m.getPeriodicity() + "' ";
        insertQuery2 += "from Publication where p_id not in (";
        insertQuery2 += "select p_id from Book UNION select p_id from Magazine_Journal)";

        try {
            stmt.executeUpdate(insertQuery2);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        String insertQuery3 = "INSERT INTO Articles (article_no, p_id, article_title, text, article_date) select ";
        insertQuery3 += a.getArticle_no() + ", p_id , '" + a.getArticle_title() + "','" + a.getText() + "' , '" + a.getArticle_date();
        insertQuery3 += "' from Magazine_Journal where p_id not in (";
        insertQuery3 += "select p_id from Articles)";
        try {
           stmt.executeUpdate(insertQuery3);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        }
        System.out.println(" New Publication Inserted");

    }
    static void updatePublication(Scanner sc, Statement stmt) {

        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. Title");
        System.out.println("2. Topic");
        System.out.println("3. Publication Date in the format yyyy-mm-dd:");
        System.out.print("Enter Option Value: ");
        int option = Integer.parseInt(sc.nextLine());
        // System.out.print("Enter Value: ");
        // String value = sc.nextLine() ;
        String updateQuery = "Update Publication set ";
        switch(option) {
            case 1:
                System.out.print("Enter new value for Title: ");
                String value1 = sc.nextLine();
                updateQuery += "title = '" + value1 + "'";
                break;
            case 2:
                System.out.print("Enter new value for Topic: ");
                String value3 = sc.nextLine();
                updateQuery += "topic = '" + value3 + "'";
                break;
            case 3:
                System.out.print("Enter new publication date in the format yyyy-mm-dd:");
                String value4 = sc.nextLine();
                updateQuery += "p_date = '" + value4 + "'";
                break;
            
            default:
                System.out.println("Invalid option");
        }
        updateQuery += " where p_id = " + p_id;
       // System.out.println("Query: "+updateQuery); 
        try {
                stmt.executeUpdate(updateQuery);
                System.out.println(" Publication updated successfully");
            } catch(SQLException e) {
                e.printStackTrace();
            }
        
    }

    static void assignEditorPublication(Scanner sc, Statement stmt) {
        Publication p = new Publication();
        System.out.println("1) Assign an editor to a Chapter ");
        System.out.println("2) Assign an editor to an Article");
        System.out.println("Choice an option:");
        int option = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the Publication id  ");
        int p_id = Integer.parseInt(sc.nextLine());
        p.setP_id(p_id) ;
        String updateQuery = ""; 
        System.out.println("Enter the Writer Id  ");
        int w_id = Integer.parseInt(sc.nextLine());

        switch(option) {
            case 1:
                Writes_or_edits_chapters wec = new Writes_or_edits_chapters() ;
                wec.setW_id(w_id);
                updateQuery += "INSERT INTO Writes_or_edits_chapters SELECT " + wec.getW_id() + ", chapter_no , p_id ";
                updateQuery+= " FROM Chapters WHERE p_id = " ;
                updateQuery+= p.getP_id() ; 
                //System.out.println("Query: "+ updateQuery); 
                break;
            case 2:
                Writes_or_edits_articles wea = new Writes_or_edits_articles() ;
                wea.setW_id(w_id);
                updateQuery += "INSERT INTO Writes_or_edits_articles SELECT " + wea.getW_id() +", article_no, p_id ";
                updateQuery+= " FROM Articles WHERE p_id = " ;
                updateQuery+= p.getP_id() ; 
               // System.out.println("Query: "+ updateQuery); 
                break;
            default:
                System.out.println("Invalid option");
        
        }
        if (option == 1 || option == 2){
            try {
                //System.out.println("Test");
                        stmt.executeQuery(updateQuery);
                        System.out.println("Editor Assigned");    
                        } catch(SQLException e) {
                            e.printStackTrace();
                        }
                    }
    }

    static void viewPublication(Scanner sc, Statement stmt, Integer w_id, String type)         
    {
        Publication p = new Publication() ;
        // Book b = new Book() ;
        // Chapter c = new Chapter() ;
        // Articles a = new Articles() ;
        Magazine_Journal  mj = new Magazine_Journal() ;
        Writes_or_edits_articles wea = new Writes_or_edits_articles();
        Writes_or_edits_chapters wec = new Writes_or_edits_chapters();

        System.out.println("1) View Publication for a Book ");
        System.out.println("2) View Publication for a Magazine ");
        System.out.println("Choice an option:");
        int option = Integer.parseInt(sc.nextLine());
        
        if (type.equals("admin")){
            System.out.println("Enter the Writer id ");
            w_id = Integer.parseInt(sc.nextLine());
        }

        String selectQuery = ""; 
        switch(option) {
            case 1:
                wea.setW_id(w_id);
                selectQuery+= "SELECT  P.*,B.edition,B.ISBN,C.chapter_no,C.chapter_title,C.chapter_date " ; 
                selectQuery+= "FROM Publication P JOIN Book B ON"  ;
                selectQuery+= " P.p_id ";
                selectQuery+= " = " ;
                selectQuery+= " B.p_id JOIN Chapters C ON C.p_id = B.p_id " ;
                selectQuery+= "where P.p_id IN" ;
                selectQuery+= "(" + "SELECT DISTINCT  p_id FROM Writes_or_edits_chapters WHERE w_id = "+ wea.getW_id()+ ")"  ;
               // System.out.println("Query: "+ selectQuery); 
            break;
            
            case 2:
                wec.setW_id(w_id);
                selectQuery+= "SELECT P.*, M.issue,M.periodicity,A.article_no,A.article_title,A.text,A.article_date " ; 
                selectQuery+= "FROM Publication P JOIN Magazine_Journal M "  ;
                selectQuery+= " ON P.p_id = M.p_id JOIN Articles A ON A.p_id = M.p_id ";
                selectQuery+= "where P.p_id IN" ;
                selectQuery+= " (SELECT DISTINCT p_id FROM Writes_or_edits_articles WHERE w_id = " +wec.getW_id()+ ")"  ;
                //System.out.println("Query: "+ selectQuery); 
            break;

            default:
                System.out.println("Invalid option");
        }
       if (option == 1 || option == 2){
        try {
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print(" Publication ID : " + rs.getInt("p_id"));
                System.out.print("\t Title " + rs.getString("title"));
                System.out.print("\t Type: " + rs.getString("type"));
                System.out.print("\t Topic: " + rs.getString("topic"));
                System.out.println("\t Publication Date in the format yyyy-mm-dd:: " + rs.getDate("p_date"));
                
            }
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
        }
    }
static void addArticle(Scanner sc, Statement stmt) {

        Articles a = new Articles() ;
        // Publication p = new Publication();
        
        System.out.print("Enter Publication id: ");
        a.setP_id(Integer.parseInt(sc.nextLine()));       
        System.out.print("Enter Article number: ");
        a.setArticle_no(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Title for the Article: ");
        a.setArticle_title(sc.nextLine());
        System.out.print("Enter Article text: ");
        a.setText(sc.nextLine());

        
        System.out.print("Enter Article Date in the format yyyy-mm-dd: ");
        
            a.setArticle_date(sc.nextLine());
        

        String insertQuery = "INSERT INTO Articles (article_no,p_id,article_title,text,article_date) Values('";
        insertQuery += a.getArticle_no() + "','" + a.getP_id() +"','" + a.getArticle_title() + "','" + a.getText() + "','";
        insertQuery += a.getArticle_date() + "')"  ;

      //  System.out.println("Query: "+insertQuery);
        try {
                stmt.executeUpdate(insertQuery);
                System.out.println(" Article added successfully");
            } catch(SQLException e) {
                e.printStackTrace();
            }
    }


    static void deleteArticle(Scanner sc, Statement stmt) {

        Articles a = new Articles() ;
    
      
        System.out.print("Enter Publication ID: ");
        a.setP_id(Integer.parseInt(sc.nextLine()));
 
        System.out.print("Enter Article Number: ");
        a.setArticle_no(Integer.parseInt(sc.nextLine()));

        String selectQuery = "Select count(*) as Number from Articles where p_id= ";
        selectQuery += a.getP_id();

        //System.out.println(selectQuery);

        String deleteQuery = "DELETE FROM Articles where p_id =";
        deleteQuery += a.getP_id() ;
        deleteQuery += " AND article_no="  ;
        deleteQuery += a.getArticle_no() ;
         
        // System.out.println("Query: "+deleteQuery);

        try {
            ResultSet rs = stmt.executeQuery(selectQuery);
            int count = 0 ;
            while(rs.next()){
                count = rs.getInt("Number");
            }
            if (count > 1) {
                stmt.executeUpdate(deleteQuery);
                System.out.println("Article Record Deleted");
            }
            else {
                System.out.println("Article Record Cannot be Deleted as there is only one Article for the given Publication ID");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }   

    }
    static void addChapter(Scanner sc, Statement stmt) {
        Chapter c = new Chapter();
        System.out.println("Enter Publication ID for the Chapter: ");
        c.setP_id(Integer.parseInt(sc.nextLine()));
        System.out.println("Enter Chapter number: ");
        c.setNumber(Integer.parseInt(sc.nextLine()));
        System.out.println("Enter Chapter Title: ");
        c.setTitle(sc.nextLine());

        
            System.out.print("Enter Chapter  Date in the format yyyy-mm-dd: ");
            c.setDate(sc.nextLine());
            
        
        //System.out.println("Checking if there are more than One Chapter");

            String insertQuery= "INSERT INTO Chapters (chapter_no,p_id,chapter_title,chapter_date) Values('";
            insertQuery += c.getNumber() + "','" + c.getP_id() +"','" + c.getTitle() + "','" + c.getdate();
            insertQuery +=  "')"  ;
            // System.out.println("Query: "+insertQuery);
             try {
                        stmt.executeUpdate(insertQuery);
                        System.out.println(" Chapter added successfully");
                     }  catch(SQLException e) {
                        e.printStackTrace();
                    }
    }

    static void deleteChapter(Scanner sc, Statement stmt, Connection conn) {
        Chapter c = new Chapter();
        System.out.println("Enter Publication ID for the Chapter: ");
        String p_id = sc.nextLine();
        System.out.println("Enter Chapter Number: ");
        String c_no = sc.nextLine();
        
        String selectQuery = "Select count(*) as Number from Chapters where p_id= ";
        selectQuery += p_id;

        System.out.println(selectQuery);

        String deleteQuery = "DELETE FROM Chapters where p_id = ";
        deleteQuery += p_id;
        deleteQuery += " and chapter_no = ";
        deleteQuery += c_no;

        //System.out.println(deleteQuery);
        try { 
            conn.setAutoCommit(false);
            stmt.executeUpdate(deleteQuery);
            int count = 0 ; 
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                count = rs.getInt("Number") ; 
            }
            if (count > 0) {
                conn.commit();
                System.out.println("Chapter Record Deleted");
            }
            else {
                conn.rollback();
                System.out.println("ABORT: Chapter Record Cannot be Delete as there is only one chapter for the given Publication ID");
            }
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            if (conn != null) {
				try {
					conn.rollback();
                    System.out.println("ABORTED : Chapter cannot be deleted");
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			}
        }   
    }



    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}