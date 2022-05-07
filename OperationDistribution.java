import java.sql.*;
import java.util.*;

public class OperationDistribution {
    // Enter a new Distribution 
    static void insertDistributor(Scanner sc, Statement stmt) {
        Distributor d = new Distributor();
        System.out.print("Enter Distributor Name: ");
        d.setD_name(sc.nextLine());
        System.out.print("Enter Distributor City: ");
        d.setCity(sc.nextLine());
        System.out.print("Enter Distributor Address: ");
        d.setAddress(sc.nextLine());
        System.out.print("Enter Distributor Type: ");
        d.setType(sc.nextLine());
        System.out.print("Enter Distributor Balance: ");
        d.setBalance(Float.parseFloat(sc.nextLine()));
        System.out.print("Enter Distributor Phone Number: ");
        d.setPhone(sc.nextLine());
        System.out.print("Enter Distributor Contact Person: ");
        d.setContact_person(sc.nextLine());
        // Creating the insert statement
        String insertQuery = "INSERT INTO Distributor (city, address, type, d_name, balance, phone, contact_person) VALUES ('";
        insertQuery += d.getCity() + "','" + d.getAddress() + "','" + d.getType() + "','";
        insertQuery += d.getD_name() + "'," + d.getBalance() + ",'" + d.getPhone() + "','" + d.getContact_person() + "')";

        //System.out.println("Query: "+insertQuery);
        // try block to insert into Distributor
        try {
            stmt.executeUpdate(insertQuery);
            System.out.println("Distributor Record Inserted");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    // Update Distributor 
    static void updateDistributor(Scanner sc, Statement stmt) {

        System.out.print("Enter Distributor ID: ");
        int d_id = Integer.parseInt(sc.nextLine());
        System.out.println("Choose the variable to be updated");
        System.out.println("1. City");
        System.out.println("2. Address");
        System.out.println("3. Type");
        System.out.println("4. Distributor Name");
        System.out.println("5. Balance");
        System.out.println("6. Phone Number");
        System.out.println("7. Contact Person");
        int option = Integer.parseInt(sc.nextLine());
        System.out.print("Enter New Value: ");
        String value = sc.nextLine();
        // Creating update query 
        String updateQuery = "Update Distributor Set ";

        switch(option) {
                    case 1:
                        updateQuery += "city = '" + value + "'";
                        break;
                    case 2:
                        updateQuery += "address = '" + value + "'";
                        break;
                    case 3:
                        updateQuery += "type = '" + value + "'";
                        break;
                    case 4:
                        updateQuery += "d_name = '" + value + "'";
                        break;
                    case 5:
                        updateQuery += "balance = " + Float.parseFloat(value);
                        break;
                    case 6:
                        updateQuery += "phone = '" + value + "'";
                        break;
                    case 7:
                        updateQuery += "contact_person = '" + value + "'";
                        break;
                    default:
                        System.out.println("Invalid option");
                }
        
        updateQuery += " where d_id = " + d_id;

      //  System.out.println("Query: "+updateQuery); 
        // try block to update Distributor-
        try {
            stmt.executeUpdate(updateQuery);
            System.out.println("Distributor Record Updated");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    // delete distributor 
    static void deleteDistributor(Scanner sc, Statement stmt) {

        System.out.print("Enter Distributor ID: ");
        int d_id = Integer.parseInt(sc.nextLine());
        // Creating the delete query
        String deleteQuery = "Delete from Distributor where d_id = " + d_id ;
        // try block to check if the delete query is working
       //  System.out.println("Query: "+deleteQuery); 
        try {
            stmt.executeUpdate(deleteQuery);
            System.out.println("Distributor Record Deleted");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    static void insertOrder(Scanner sc, Statement stmt, int user_id, String type, Connection conn) {
        Order o = new Order();

        System.out.print("Enter Order Type: ");
        o.setType(sc.nextLine());
        System.out.print("Enter Order Copies: ");
        String copies =(sc.nextLine());
        System.out.print("Enter Order Price: ");
        String price = sc.nextLine();
        System.out.print("Enter Order Shipping Cost: ");
        String shipping_cost = sc.nextLine();
        System.out.print("Enter Order Date: ");
        o.setOrder_date(sc.nextLine());
        System.out.print("Enter Order Due Date: ");
        o.setDue_date(sc.nextLine());
        System.out.print("Enter Publication ID: ");
        String p_id = sc.nextLine();
        String d_id = "";
        if(type.equals("admin")) {
            System.out.print("Enter Distributor ID: ");
            d_id = sc.nextLine();
        } else 
            d_id = ""+user_id;
        // Creating Insert query for Orders tables
        String insertQuery = "INSERT INTO `Order` (type, copies, price, shipping_cost, order_date,due_date,p_id,d_id) VALUES ('";
        insertQuery += o.getType() + "'," + copies + "," + price + "," + shipping_cost + ",'";
        insertQuery += o.getOrder_date() + "','" + o.getDue_date() + "'," + p_id + "," + d_id + ")";
        // try block to check if the query exceute successfully 
        // System.out.println("Query: "+insertQuery);
        float bill = Float.parseFloat(copies)*Float.parseFloat(price)+Float.parseFloat(shipping_cost);
        String updateQuery = "Update Distributor set balance = balance + " + bill + " where d_id = " + d_id;
        try {
            conn.setAutoCommit(false);
            if(stmt.executeUpdate(insertQuery)==1) {
                stmt.executeUpdate(updateQuery);
                conn.commit();
                System.out.println("Order Record Inserted");
                System.out.println("Bill Amount: "+ bill);
                System.out.println("Distributor Balance Updated");
            } else {
				conn.rollback();
				System.out.println("ABORTED: Order Record Not Inserted");
			}
            conn.setAutoCommit(true);
        } catch(SQLException e) {
            if (conn != null) {
				try {
					conn.rollback();
                    System.out.println("ABORTED : Order Record Not Inserted");
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					e.printStackTrace();
				}
			}
        }
    }
    // Billing distributor 
    static void billDistributor(Scanner sc, Statement stmt) {

        System.out.print("Enter Order ID: ");
        int o_id = Integer.parseInt(sc.nextLine());
        int d_id = 0;
        float bill = 0;

        try {
            // Creating query for billing distributor
            String selectQuery = "Select d_id, (Copies*Price + Shipping_cost) as bill from `Order` where o_id=" + o_id;
            ResultSet result = stmt.executeQuery(selectQuery);
            // try block to check the query execution in the Order table
            if(result.next()) {
                d_id = Integer.parseInt(result.getString("d_id"));
                bill = Float.parseFloat(result.getString("bill"));
            } else {
                System.out.println("Invalid Order");
                throw new SQLException();
            }
            // Creating update query to update distributor balance 
            String updateQuery = "Update Distributor set balance = balance - " + bill + " where d_id = " + d_id;
            //System.out.println("Query: "+ updateQuery); 
            System.out.println("Payment Amount: "+ bill);
            stmt.executeUpdate(updateQuery);
            System.out.println("Distributor Balance Updated");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
