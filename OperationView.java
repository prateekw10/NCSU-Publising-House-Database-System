import java.sql.*;

public class OperationView {

    static void viewArticles(Statement stmt) {
            String selectQuery = "Select * from Articles";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Publication ID : " + rs.getInt("p_id"));
                    System.out.print("\tArticle No: " + rs.getInt("article_no"));
                    System.out.print("\tArticle Title : " + rs.getString("article_title"));
                    System.out.print("\tArticle Text: " + rs.getString("text"));
                    System.out.println("\tArticle Date: " + rs.getString("article_date"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewBook(Statement stmt) {
            String selectQuery = "Select * from Book";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Publication ID : " + rs.getInt("p_id"));
                    System.out.print("\tEdition: " + rs.getInt("edition"));
                    System.out.println("\tISBN: " + rs.getString("ISBN"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewChapters(Statement stmt) {
            String selectQuery = "Select * from Chapters";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Publication ID : " + rs.getInt("p_id"));
                    System.out.print("\tChapter No: " + rs.getInt("chapter_no"));
                    System.out.print("\tChapter Title: " + rs.getString("chapter_title"));
                    System.out.println("\tChapter Date: " + rs.getString("chapter_date"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewDistributor(Statement stmt) {
            String selectQuery = "Select * from Distributor";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("ID : " + rs.getInt("d_id"));
                    System.out.print("\tName: " + rs.getString("d_name"));
                    System.out.print("\tCity: " + rs.getString("city"));
                    System.out.print("\tAddress: " + rs.getString("address"));
                    System.out.print("\tType: " + rs.getString("type"));
                    System.out.print("\tBalance: " + rs.getFloat("balance"));
                    System.out.print("\tPhone: " + rs.getString("phone"));
                    System.out.println("\tContact Person: " + rs.getString("contact_person"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewMagazineJournal(Statement stmt) {
            String selectQuery = "Select * from Magazine_Journal";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Publication ID : " + rs.getInt("p_id"));
                    System.out.print("\tIssue: " + rs.getString("issue"));
                    System.out.println("\tPeriodicity: " + rs.getString("periodicity"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewOrder(Statement stmt) {
            String selectQuery = "Select * from `Order`";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("ID : " + rs.getInt("o_id"));
                    System.out.print("\tType: " + rs.getString("Type"));
                    System.out.print("\tCopies: " + rs.getInt("Copies"));
                    System.out.print("\tPrice: " + rs.getFloat("Price"));
                    System.out.print("\tShipping Cost: " + rs.getFloat("Shipping_cost"));
                    System.out.print("\tOrder Date: " + rs.getString("Order_date"));
                    System.out.print("\tDue Date: " + rs.getString("Due_date"));
                    System.out.print("\tPublication ID: " + rs.getInt("p_id"));
                    System.out.println("\tDistributor ID: " + rs.getInt("d_id"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewPayment(Statement stmt) {
            String selectQuery = "Select * from Payments";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Payment ID: " + rs.getInt("pay_id"));
                    System.out.print("\tPay Date: " + rs.getString("pay_date"));
                    System.out.print("\tStart Date: " + rs.getString("start_date"));
                    System.out.print("\tend Date: " + rs.getString("end_date"));
                    System.out.print("\tclaim Date: " + rs.getString("claim_date"));
                    System.out.print("\tPayment Amount: " + rs.getFloat("paymentAmount"));
                    System.out.print("\tWriter id: " + rs.getInt("w_id"));
                    System.out.println("\tWork type: " + rs.getString("work_type"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewPublication(Statement stmt) {
            String selectQuery = "Select * from Publication";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Publication ID: " + rs.getInt("p_id"));
                    System.out.print("\tPublication title: " + rs.getString("title"));
                    System.out.print("\tPublication type: " + rs.getString("type"));
                    System.out.print("\tPublication topic:" + rs.getString("topic"));
                    System.out.println("\tPublication Date: " + rs.getString("p_date"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewWriter(Statement stmt) {
            String selectQuery = "Select * from Writer";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Writer ID: " + rs.getInt("w_id"));
                    System.out.print("\tWriter name: " + rs.getString("w_name"));
                    System.out.println("\tWriter Type: " + rs.getString("type"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }
        
        static void viewWrites_or_edits_articles(Statement stmt) {
            String selectQuery = "Select * from Writes_or_edits_articles";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Writer id: " + rs.getInt("w_id"));
                    System.out.print("\tArticle number: " + rs.getInt("article_no"));
                    System.out.println("\tPublication number: " + rs.getInt("p_id"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }

        static void viewWrites_or_edits_chapters(Statement stmt) {
            String selectQuery = "Select * from Writes_or_edits_chapters";
            // try block for the Query.
            try {
                ResultSet rs = stmt.executeQuery(selectQuery);
                while(rs.next()){
                    System.out.print("Writer id: " + rs.getInt("w_id"));
                    System.out.print("\tChapter number: " + rs.getInt("chapter_no"));
                    System.out.println("\tPublication ID: " + rs.getInt("p_id"));
                }
                System.out.println("");
            } catch(SQLException e) {
                e.printStackTrace();
            }   
        }
}
