
import java.sql.*;
import java.util.*;

import java.text.SimpleDateFormat;  
import java.util.Date;  
/*
Class to impletement the oprtation of Reporting in the Database. The Operation that are performed are:
1. Enter Book Edition
2. Enter Issue of Publication
3. Update Book Edition
4. Update Issue of Publication
5. Delete Book Edition or Publication Issue
6. Enter an article
7. Enter a chapter
8. Update an article
9. Update a chapter
10. Update text of an article
11. Find books
12. Find articles
13. Enter payment details
14. Claim payment
*/
public class OperationProduction {
    // Function to insert a New Edition into the Book table.
    static void insertEdition(Scanner sc, Statement stmt) { 
        Book b = new Book();
        Publication p = new Publication();
        Chapter c = new Chapter();
        System.out.print("Enter Publication title: ");
        p.setTitle(sc.nextLine());
        // System.out.print("Enter Publication type: ");
        // p.setType(sc.nextLine());
        p.setType("Book");
        System.out.print("Enter Publication topic: ");
        p.setTopic(sc.nextLine());
        System.out.print("Enter Publication date in the format yyyy-mm-dd: ");
        p.setP_date(sc.nextLine());
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

        String insertQuery1 = "INSERT INTO Publication (title,type,topic,p_date) VALUES ('";
        insertQuery1 += p.getTitle() + "','" + p.getType() + "','" + p.getTopic() + "','";
        insertQuery1 += p.getP_date() + "')";
        // try block for the Query for Publication.

        String insertQuery2 = "INSERT INTO Book (p_id,edition,ISBN) select p_id , ";
        insertQuery2 += b.getEdition() + ",'" + b.getIsbn() + "'";
        insertQuery2 += " from Publication where p_id not in (";
        insertQuery2 += "select p_id from Book UNION select p_id from Magazine_Journal)";

        

        String insertQuery3 = "INSERT INTO Chapters (chapter_no, p_id, chapter_title, chapter_date) select ";
        insertQuery3 += c.getNumber() + ", p_id , '" + c.getTitle() + "','" + c.getdate() + "'";
        insertQuery3 += " from Book where p_id not in (";
        insertQuery3 += "select p_id from Chapters)";
        
        // try block for the Query for Book.
        try {
            stmt.executeUpdate(insertQuery1);
            System.out.println("Publication Inserted");
            stmt.executeUpdate(insertQuery2);
            System.out.println("Book Edition Inserted");
            stmt.executeUpdate(insertQuery3);
            System.out.println("Chapter Inserted");
        } catch(SQLException e) {
            e.printStackTrace();
        }
     

        //System.out.println(insertQuery1);
        //System.out.println(insertQuery2);
        //System.out.println(insertQuery3);

        
    }
    // Enter Issue of Publication.
    static void insertIssue(Scanner sc, Statement stmt) {
         
        Magazine_Journal m = new Magazine_Journal();
        Publication p = new Publication();
        Articles a = new Articles();
        System.out.print("Enter Publication title: ");
        p.setTitle(sc.nextLine());
        System.out.print("Enter Publication Type (Magazine/Journal): ");
        p.setType(sc.nextLine());
        System.out.print("Enter Publication topic: ");
        p.setTopic(sc.nextLine());
        System.out.print("Enter Publication date in the format yyyy-mm-dd: ");
        p.setP_date(sc.nextLine());
    
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
        
        // Creating Insert Query for Publication
        String insertQuery1 = "INSERT INTO Publication (title,type,topic,p_date) VALUES ('";
        insertQuery1 += p.getTitle() + "','" + p.getType() + "','" + p.getTopic() + "','";
        insertQuery1 += p.getP_date() + "')";
        // try block for the Query for Publication.

        // Creating Insert Query for Magazine_Journal
        String insertQuery2 = "INSERT INTO Magazine_Journal (p_id,issue,periodicity) select ";
        insertQuery2 += "p_id , " + m.getIssue() + ",'" + m.getPeriodicity() + "' ";
        insertQuery2 += "from Publication where p_id not in (";
        insertQuery2 += "select p_id from Book UNION select p_id from Magazine_Journal)";

        // Creating Insert Query for Articles
        String insertQuery3 = "INSERT INTO Articles (article_no, p_id, article_title, text, article_date) select ";
        insertQuery3 += a.getArticle_no() + ", p_id , '" + a.getArticle_title() + "','" + a.getText() + "' , '" + a.getArticle_date();
        insertQuery3 += "' from Magazine_Journal where p_id not in (";
        insertQuery3 += "select p_id from Articles)";
        try {
            stmt.executeUpdate(insertQuery1);
            stmt.executeUpdate(insertQuery2);
            stmt.executeUpdate(insertQuery3);
            System.out.println("Publication Inserted");
            System.out.println("Article Inserted");
            System.out.println("Issue Inserted");
        } catch(SQLException e) {
            e.printStackTrace();
        }
       

        //System.out.println(insertQuery1);
        //System.out.println(insertQuery2);
        //System.out.println(insertQuery3);

        
    }
    // Update Book Edition
    static void updateEdition(Scanner sc, Statement stmt) {
        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. Edition");
        System.out.println("2. ISBN");
        int option = Integer.parseInt(sc.nextLine());
        // Creating Update query
        String updateQuery = "Update Book Set ";

        switch(option) {
                    case 1:
                        System.out.print("Enter New Value: ");
                        int value1 = Integer.parseInt(sc.nextLine());
                        updateQuery += "edition = " + value1;
                        break;
                    case 2:
                    System.out.print("Enter New Value: ");
                        String value2 = sc.nextLine();
                        updateQuery += "ISBN = '" + value2 + "'";
                        break;
                    default:
                        System.out.println("Invalid option");
                }
        
        updateQuery += " where p_id = " + p_id;
        if(option==1 || option==2){
        //System.out.println("Query: "+updateQuery); 
        // try block for the updateQuery on Book table.
         try {
             stmt.executeUpdate(updateQuery);
             System.out.println("Edition Updated");
         } catch(SQLException e) {
             e.printStackTrace();
        }
    }
    }
    // Update Issue of Publication
    static void updateIssue(Scanner sc, Statement stmt) {
        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. Issue");
        System.out.println("2. Periodicity");
        int option = Integer.parseInt(sc.nextLine());

        
        // Creating Update query
        String updateQuery = "Update Magazine_Journal Set ";

        switch(option) {
                    case 1:
                        System.out.print("Enter New Value: ");
                        int value1 = Integer.parseInt(sc.nextLine());
                        updateQuery += "issue = " + value1;
                        break;
                    case 2:
                        System.out.print("Enter New Value: ");
                        String value2 = sc.nextLine();
                        updateQuery += "periodicity = '" + value2 + "'";
                        break;
                    default:
                        System.out.println("Invalid option");
                }
        
        updateQuery += " where p_id = " + p_id;
        if(option == 1 || option==2){
       //  System.out.println("Query: "+updateQuery); 
        // try block for the updateQuery on Magazine_Journal table.
         try {
             stmt.executeUpdate(updateQuery);
             System.out.println("Issue Updated");
         } catch(SQLException e) {
             e.printStackTrace();
         }
        }

    }
    // Delete Book Edition or Publication Issue
    static void deleteEdition_Issue(Scanner sc, Statement stmt) {

        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        // Creating Update query
        String deleteQuery = "Delete from Publication where p_id = " + p_id;

       //  System.out.println("Query: "+deleteQuery); 
        // try block for the delete Query on Book table.
        try {
             stmt.executeUpdate(deleteQuery);
             System.out.println("Deleted Publication");
        } catch(SQLException e) {
             e.printStackTrace();
        }
    }

    // Enter an article
    static void insertArticle(Scanner sc, Statement stmt) {
        Articles a = new Articles();
        System.out.print("Enter Publication id: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Article number: ");
        a.setArticle_no(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Article title: ");
        a.setArticle_title(sc.nextLine());
        System.out.print("Enter Article text: ");
        a.setText(sc.nextLine());
        System.out.print("Enter Article date in the format yyyy-mm-dd: ");
    
        a.setArticle_date(sc.nextLine());

        

        
        // Creating Insert query
        String insertQuery1 = "INSERT INTO Articles (article_no, p_id, article_title, text, article_date) VALUES (";
        insertQuery1 += a.getArticle_no() + "," + p_id + ",'" + a.getArticle_title() + "','" + a.getText() + "','";
        insertQuery1 += a.getArticle_date() + "')";
        //System.out.println(insertQuery1);
        // try block for the Insert Query on Articles table.
        try {
            stmt.executeUpdate(insertQuery1);
            
        } catch(SQLException e) {
            e.printStackTrace();
        
        
          

    
    }   
    }
    // Enter an Chapter
    static void insertChapter(Scanner sc, Statement stmt) {
        
        Chapter c = new Chapter();
        System.out.print("Enter Publication id: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Chapter number: ");
        c.setNumber(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Chapter title: ");
        c.setTitle(sc.nextLine());
        System.out.print("Enter Chapter date in the format yyyy-mm-dd: ");
        c.setDate(sc.nextLine());

        

        // Creating Insert query
        String insertQuery1 = "INSERT INTO Chapters (chapter_no, p_id, chapter_title, chapter_date) Values(";
        insertQuery1 += c.getNumber() + "," + p_id +",'" + c.getTitle() + "','" + c.getdate() + "')";
        //System.out.println(insertQuery1);
        // try block for the insert Query on Chapters table.
        try {
            stmt.executeUpdate(insertQuery1);
            
        } catch(SQLException e) {
            e.printStackTrace();
        }

       


    }
    // Update an Chapter 
    static void updateChapter(Scanner sc, Statement stmt) {
        
        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Chapter no: ");
        int c_no = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. Chapter Title");
        System.out.println("2. Date");
        System.out.println("3. Author name (Only if author assigned)");
        System.out.println("4. Topic");
        int option = Integer.parseInt(sc.nextLine());
        // Update query
        String updateQuery = "Update ";
        switch(option) {
            case 1:
                System.out.print("Enter New Value: ");
                String value1 = sc.nextLine();
                updateQuery += "Chapters set chapter_title = '" + value1 + "'";
                updateQuery += " where p_id=" + p_id +" and chapter_no=" +c_no;
                break;
            case 2:
                System.out.print("Enter New Value: ");
                try{
                    String value2 = sc.nextLine();
                    updateQuery += "Chapters set chapter_date = '" + value2 + "'";
                    updateQuery += " where p_id=" + p_id +" and chapter_no=" +c_no;
                }
                catch(Exception e) {
                    e.printStackTrace();  
                }
                break;
            case 3:
                System.out.print("Enter New Author name: ");
                String name = sc.nextLine();
                System.out.println("Enter Old Author Id");
                int a_id = Integer.parseInt(sc.nextLine());
                updateQuery += "Writes_or_edits_chapters SET w_id = ";
                updateQuery += "(SELECT w_id From Writer WHERE w_name='"+name+"' and type like '%Author') WHERE "; 
                updateQuery += "chapter_no="+c_no+" and p_id ="+p_id+" and w_id = "+a_id;
                break;
            case 4:
                System.out.print("Enter New Value: ");
                try{
                    String value2 = sc.nextLine();
                    updateQuery += "Publication set topic = '" + value2 + "'";
                    updateQuery += " where p_id=" + p_id;
                }
                catch(Exception e) {
                    e.printStackTrace();  
                }
                break;
            default:
                System.out.println("Invalid option");
        }
        if(option == 1 || option==2 || option==3 || option==4){
       // System.out.println("Query: "+updateQuery); 
        // try block for the Update Query on Chapters table.
        try {
             stmt.executeUpdate(updateQuery);
             System.out.println("Chapter Updated");
         } catch(SQLException e) {
             e.printStackTrace();
         }
        }

    }   
    // Enter an article 
    static void updateArticle(Scanner sc, Statement stmt) {
        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter article no: ");
        int a_no = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. Article title");
        System.out.println("2. Date");
        System.out.println("3. Author name (Only if author assigned)");
        System.out.println("4. Topic");
        int option = Integer.parseInt(sc.nextLine());
        // Update query
        String updateQuery = "Update ";

        switch(option) {
            case 1:
                System.out.print("Enter New Value: ");
                String value1 = sc.nextLine();
                updateQuery += "Articles set article_title = '" + value1 + "'";
                updateQuery += " where p_id=" + p_id +" and article_no=" +a_no;
                break;
            case 2:
                System.out.print("Enter New Value: ");
                try{
                    String value2 = sc.nextLine();
                    updateQuery += "Articles set article_date = '" + value2 + "'";
                    updateQuery += " where p_id=" + p_id +" and article_no=" +a_no;
                }
                catch(Exception e) {
                    e.printStackTrace();  
                }
                break;
            case 3:
                System.out.print("Enter New Author name: ");
                String name = sc.nextLine();
                System.out.println("Enter Old Author Id");
                int a_id = Integer.parseInt(sc.nextLine());
                updateQuery += "Writes_or_edits_articles SET w_id = ";
                updateQuery += "(SELECT w_id From Writer WHERE w_name='"+name+"' and type like '%Author') WHERE "; 
                updateQuery += "article_no="+a_no+" and p_id ="+p_id+" and w_id = "+a_id;
                break;
            case 4:
                System.out.print("Enter New Value: ");
                try{
                    String value2 = sc.nextLine();
                    updateQuery += "Publication set topic = '" + value2 + "'";
                    updateQuery += " where p_id=" + p_id;
                }
                catch(Exception e) {
                    e.printStackTrace();  
                }
                break;
            default:
                System.out.println("Invalid option");
    }
        // try block for the Update Query on Article table.
        if(option == 1 || option==2 || option==3 || option==4){
        // System.out.println("Query: "+updateQuery); 
        try {
             stmt.executeUpdate(updateQuery);
             System.out.println("Article Updated");
         } catch(SQLException e) {
             e.printStackTrace();
         }
        }

    }
    // Insert into Artiles text
    static void insertText(Scanner sc, Statement stmt) {  
        
        System.out.print("Enter Publication ID: ");
        int p_id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Article No: ");
        int num = Integer.parseInt(sc.nextLine());
        System.out.println("Enter text of the article");
        String text = sc.nextLine();
        // creating the update query.
        String updateQuery = "Update Articles Set text = '" + text + "' where p_id = "+p_id+ " and article_no="+num;

        
        // try block for the update articles query
       //  System.out.println("Query: "+updateQuery); 
         try {
             stmt.executeUpdate(updateQuery);
             System.out.println("Text Updated");
         } catch(SQLException e) {
             e.printStackTrace();
         }

    }

    static void findBooks(Scanner sc, Statement stmt) { 
        
        System.out.print("Enter Author name: ");
        String name= sc.nextLine();
        System.out.print("Enter topic : ");
        String topic= sc.nextLine();
        System.out.print("Enter Date in the format yyyy-mm-dd:");
        String date="";
        String query="";
        // Creating the select statement for the books tables.
            date = sc.nextLine();
            query += "Select Distinct P.*,B.*,W.* from Publication P ";
            query += "join Book B on B.p_id = P.p_id join Chapters C on C.p_id = B.p_id ";
            query += "join Writes_or_edits_chapters wec on B.p_id = wec.p_id and C.chapter_no = wec.chapter_no ";
            query += "join Writer W on W.w_id = wec.w_id where ";
            query += "W.w_name = '"+name+"' and P.Topic='"+topic+"' and P.p_date='"+date+"'";
        
        
        // try block for the select query in the book table.
        System.out.println("BOOKS: \n");
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("title"));          
            }   
        } catch(SQLException e) {
            e.printStackTrace();
        }   
        //System.out.println(query);

    }
    // Select statement for find Articles.
    static void findArticles(Scanner sc, Statement stmt) { 
        
        System.out.print("Enter Author name: ");
        String name= sc.nextLine();
        System.out.print("Enter topic : ");
        String topic= sc.nextLine();
        System.out.print("Enter Date in the format yyyy-mm-dd:");
        String date="";
        String query="";
        // Creating select statement 
            date = sc.nextLine();
            query += "Select Distinct P.*,MJ.*,A.*,W.* from Publication P ";
            query += "join Magazine_Journal MJ on MJ.p_id = P.p_id ";
            query += "join Articles A on A.p_id = MJ.p_id join Writes_or_edits_articles wea ";
            query += "on A.p_id = wea.p_id and A.article_no = wea.article_no ";
            query += "join Writer W on W.w_id = wea.w_id where ";
            query += "W.w_name = '"+name+"' and P.Topic='"+topic+"' and P.p_date='"+date+"'";
        
        // try block for the select query. 
        System.out.println("ARTICLES: \n");
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("Publication: "+rs.getString("title"));
                System.out.println("Article: "+rs.getString("article_title"));
                System.out.println("");
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }   
        //System.out.println(query);
    }
    // Insert into payments table
    static void insertPayment(Scanner sc, Statement stmt) { 
       
        Payments p = new Payments();
        
        System.out.print("Enter Payment date in the format yyyy-mm-dd: ");
        p.setPay_date(sc.nextLine());
        System.out.print("Enter payment start date in the format yyyy-mm-dd: ");
        p.setStart_date(sc.nextLine());
        System.out.print("Enter payment end date in the format yyyy-mm-dd: ");
        p.setEnd_date(sc.nextLine());
        System.out.print("Enter payment claim date in the format yyyy-mm-dd: ");
        p.setClaim_date(sc.nextLine());
        
         
        System.out.print("Enter Payment amount: ");
        p.setPaymentAmount(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Author or Editor ID: ");
        p.setW_id(Integer.parseInt(sc.nextLine()));
        System.out.print("Enter Work Type: ");
        p.setWork_type(sc.nextLine());
        // Insert statement for payments table
        String query="";
        query += "Insert into Payments (pay_date, start_date, end_date, claim_date, paymentAmount, w_id, work_type) VALUES ('";
        query += p.getPay_date()+"','"+p.getStart_date()+"','"+p.getEnd_date()+"','"+p.getClaim_date()+"',";
        query += p.getPaymentAmount()+","+p.getW_id()+",'"+p.getWork_type()+"')";
           
        // try block for the Insert for  Payments.
        try {
            stmt.executeUpdate(query);
            System.out.println("Payment Inserted");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(query);
    }
    // Claim payment
    static void claimPayment(Scanner sc, Statement stmt) { 
        
        System.out.print("Enter Editor or Author name: ");
        // Creating Select query for checking payments.
        String name = sc.nextLine();
        String query="";
        query += "Select P.claim_date, P.pay_id from Payments P join Writer W on W.w_id = P.w_id where ";
        query += "W.w_name = '"+name+"'";
        //System.out.println(query);
        // try block for the Query for Claim Payments.
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println("Payment ID: "+rs.getString("pay_id"));
                System.out.println("Claim Date: "+rs.getString("claim_date"));
                System.out.println("");
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }   
        
    }

    static void updateClaimPayment(Scanner sc, Statement stmt) {
        System.out.print("Enter Payment ID: ");
        // Creating Select query for checking payments.
        String id = sc.nextLine();
        String query="";
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        query += "Update Payments set claim_date = '" + formatter.format(date);
        query += "' where pay_id = '"+id+"'";
        //System.out.println(query);
        // try block for the Query for Claim Payments.
        try {
            stmt.executeUpdate(query);
            System.out.println("Payment Claimed");
        } catch(SQLException e) {
            e.printStackTrace();
        }  
    }

    // Closing the connection in case of failure 
    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }
    // Closing the Statement in case of failure 
    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }
    // Closing the ResultSet in case of failure 
    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
