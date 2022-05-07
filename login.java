import java.sql.*;
import java.util.Scanner;

// This class login provides rights to the dababase administrator and establish connections to the MariaDb 

public class login {
    

    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/sgupta45";
    private static final String user = "sgupta45";
    private static final String password = "200420103";

    public static Connection connection = null;
    public static Statement stmt = null;
    public static ResultSet result = null;

    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                
                System.out.println("Connecting to database...");
                connection = DriverManager.getConnection(jdbcURL, user, password);
                stmt = connection.createStatement();
                Scanner sc = new Scanner(System.in);
                String type = "";
                int user_id = 0;
                
                System.out.println("\n\t\tWelcome to WolfPub DB Management System\n\n");
                boolean firstCheck = false;
                while (true) {
                    System.out.println("Please enter your username: ");
                    String loginUser = sc.nextLine();
                    System.out.println("Please enter your password: ");
                    String loginPwd = sc.nextLine();

                    String sqlCred = "select * from LoginDetails where username = '" + loginUser + "' and password='" + loginPwd + "'";
                    result = stmt.executeQuery(sqlCred);
                    if(result.next()) {
                        user_id = result.getInt("user_id");
                        type = result.getString("type");
                        System.out.println("Logged in as " + type);
                        System.out.println("Login successful!!! \n");
                        mainMenu(user_id, type, stmt, sc);
                        break;
                    } else {
                        System.out.println("Incorrect Credentials Entered!!! \n");
                        System.out.println("Do you want to try again? (y/n) ");
                        String ans = sc.nextLine();
                        if(!ans.equalsIgnoreCase("y")) {
                            break;
                        }
                    }
                }               
            } finally {
                close(result);
                close(stmt);
                close(connection);
                System.out.println("\n\nClosing database connection..\n");
            }
        }
        catch (Throwable oops) {
            oops.printStackTrace();
        }
    }
    
    static void mainMenu(int user_id, String type, Statement stmt, Scanner sc) {
        int option = 0;
        do {
            System.out.println("Choose Operation:");
            System.out.println("1) Editing and Publishing");
            System.out.println("2) Production of a book edition or of an issue of a publication");
            System.out.println("3) Distribution");
            System.out.println("4) Reports");
            System.out.println("5) Display Menu");
            System.out.println("6) Switch User");
            System.out.println("7) Exit");
            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1:
                    editingMenu(stmt, sc, user_id, type);
                    break;
                case 2:
                    publicationMenu(stmt, sc, type);
                    break;
                case 3:
                    distributionMenu(stmt, sc, user_id, type);
                    break;
                case 4:
                    if(type.equals("admin"))
                        reportMenu(stmt, sc);
                    else    
                        System.out.println("Access Denied\n");
                    break;
                case 5:
                    if(type.equals("admin"))
                        selectMenu(stmt, sc);
                    else    
                        System.out.println("Access Denied\n");
                    break;
                case 6:
                    System.out.println("");
                    System.out.println("Switching User......");
                    System.out.println("");
                    main(null);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=7);
    }

    static void editingMenu(Statement stmt, Scanner sc, int user_id, String type) {
        int option = 0;
        OperationEditor obj = new OperationEditor();
        do {
            System.out.println("\nChoose Editing and Publishing Operation Option:");
            System.out.println("1) Enter Publicatiom");
            System.out.println("2) Update Publication Information");
            System.out.println("3) Assign editor to Publication");
            System.out.println("4) View Publication for a specific editor.");
            System.out.println("5) Add Article");
            System.out.println("6) Delete Article ");
            System.out.println("7) Add chapter/section in a  Book");
            System.out.println("8) Delete chapter/section in a  Book");
            System.out.println("9) Exit to main menu");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch(option) {
                case 1:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertPublication(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 2:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.updatePublication(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 3:
                    if(type.equals("admin"))
                        obj.assignEditorPublication(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 4:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.viewPublication(sc, stmt, user_id, type);
                        //obj.viewPublication(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 5:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.addArticle(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 6:
                    if(type.equals("admin") || type.equals("editor"))
                    obj.deleteArticle(sc, stmt);
                    else
                    System.out.println("Access Denied\n");
                    break;
                case 7:
                    
                    if(type.equals("admin") || type.equals("editor"))
                        obj.addChapter(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 8:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.deleteChapter(sc, stmt, connection);
                    else
                        System.out.println("Access Denied\n");
                    break;
                    
                case 9:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=9);
    }

    static void publicationMenu(Statement stmt, Scanner sc, String type) {
        int option = 0;
        OperationProduction obj = new OperationProduction();
        do {
            System.out.println("\nChoose Production of a Publication Operation Option:");
            System.out.println("1) Enter Book Edition");
            System.out.println("2) Enter Issue of Publication");
            System.out.println("3) Update Book Edition");
            System.out.println("4) Update Issue of Publication");
            System.out.println("5) Delete Book Edition or Publication Issue");
            System.out.println("6) Enter an article");
            System.out.println("7) Enter a chapter");
            System.out.println("8) Update an article");
            System.out.println("9) Update a chapter");
            System.out.println("10) Update text of an article");
            System.out.println("11) Find books");
            System.out.println("12) Find articles");
            System.out.println("13) Enter payment details");
            System.out.println("14) Track payment claims");
            System.out.println("15) Claim Payments");
            System.out.println("16) Exit to main menu");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch(option) {
                case 1:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertEdition(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 2:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertIssue(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 3:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.updateEdition(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 4:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.updateIssue(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 5:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.deleteEdition_Issue(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 6:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertArticle(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 7:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertChapter(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 8:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.updateArticle(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 9:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.updateChapter(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 10:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.insertText(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 11:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.findBooks(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 12:
                    if(type.equals("admin") || type.equals("editor"))
                        obj.findArticles(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 13:
                    if(type.equals("admin") || type.equals("billing"))
                        obj.insertPayment(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 14:
                    if(type.equals("admin") || type.equals("billing"))
                        obj.claimPayment(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 15:
                    if(type.equals("admin") || type.equals("billing"))
                        obj.updateClaimPayment(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 16:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=16);
    }

    static void distributionMenu(Statement stmt, Scanner sc, int user_id, String type) {
        int option = 0;
        OperationDistribution obj = new OperationDistribution();
        do {
            System.out.println("\nChoose Distribution Operation Option:");
            System.out.println("1) Enter Distributor");
            System.out.println("2) Update Distributor Information");
            System.out.println("3) Delete a Distributor");
            System.out.println("4) Input Orders from a Distributor and bill distributor");
            System.out.println("5) Recieve Payment for an order and update outstanding balance");
            System.out.println("6) Exit to main menu");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch(option) {
                case 1:
                    if(type.equals("admin"))
                        obj.insertDistributor(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 2:
                    if(type.equals("admin"))
                        obj.updateDistributor(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 3:
                    if(type.equals("admin"))
                        obj.deleteDistributor(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 4:
                    if(type.equals("admin") || type.equals("distrib"))
                        obj.insertOrder(sc, stmt, user_id, type, connection);
                        //obj.insertOrder(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 5:
                    if(type.equals("admin") || type.equals("billing"))
                        obj.billDistributor(sc, stmt);
                    else
                        System.out.println("Access Denied\n");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=6);
    }

    static void reportMenu(Statement stmt, Scanner sc) {
        int option = 0;
        OperationReports obj = new OperationReports();
        do {
            System.out.println("\nChoose Reports Operation Option:");
            System.out.println("1) Number and total price of copies of each publication bought per distributor per month");
            System.out.println("2) Total revenue of the publishing house");
            System.out.println("3) total expenses (i.e., shipping costs and salaries)");
            System.out.println("4) Calculate the total current number of distributors"); 
            System.out.println("5) Calculate total revenue (since inception) per city.");
            System.out.println("6) Calculate total revenue (since inception) per distributor.");
            System.out.println("7) Calculate total revenue (since inception) per location.");
            System.out.println("8) Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work). ");
            System.out.println("9) Exit to main menu");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch(option) {
                case 1:
                    obj.totalPrice(sc, stmt);
                    break;
                case 2:
                    obj.totalRevenue(sc, stmt);
                    break;
                case 3:
                    obj.totalExpenses(sc, stmt);
                    break;
                case 4:
                    obj.totalDistributors(sc, stmt);
                    break;
                case 5:
                    obj.totalRevenueCity(sc, stmt);
                    break;
                case 6:
                    obj.totalRevenueDistributor(sc, stmt);
                    break;
                case 7:
                    obj.totalRevenueAddress(sc, stmt);
                    break;
                case 8:
                    obj.totalPayments(sc, stmt);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=9);
    }

    static void selectMenu(Statement stmt, Scanner sc) {
        int option = 0;
        OperationView obj = new OperationView();
        do {
            System.out.println("\nChoose Table to View:");
            System.out.println("1) Article");
            System.out.println("2) Book");
            System.out.println("3) Chapter");
            System.out.println("4) Distributor"); 
            System.out.println("5) Magazine_Journal");
            System.out.println("6) Order");
            System.out.println("7) Payments");
            System.out.println("8) Publication");
            System.out.println("9) Writer");
            System.out.println("10) Writes_or_edits_articles");
            System.out.println("11) Writes_or_edits_chapters");
            System.out.println("12) Exit to main menu");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("");

            switch(option) {
                case 1:
                    obj.viewArticles(stmt);
                    break;
                case 2:
                    obj.viewBook(stmt);
                    break;
                case 3:
                    obj.viewChapters(stmt);
                    break;
                case 4:
                    obj.viewDistributor(stmt);
                    break;
                case 5:
                    obj.viewMagazineJournal(stmt);
                    break;
                case 6:
                    obj.viewOrder(stmt);
                    break;
                case 7:
                    obj.viewPayment(stmt);
                    break;
                case 8:
                    obj.viewPublication(stmt);
                    break;
                case 9:
                    obj.viewWriter(stmt);
                    break;
                case 10:
                    obj.viewWrites_or_edits_articles(stmt);
                    break;
                case 11:
                    obj.viewWrites_or_edits_chapters(stmt);
                    break;
                case 12:
                    break;
                default:
                    System.out.println("Invalid Option\n\n");
            }
        } while (option!=12);
    }

    static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (Throwable whatever) {
            }
        }
    }

}