/*
Class to impletement the oprtation of Reporting in the Database. The Operation that are performed are:
    1. Finding Number and total price of copies of each publication bought per distributor per distributor per month.
    2. Total Revenue of the Publication house.
    3. Total expenses (i.e., shipping costs and salaries) of the pubication house.
    4. Calculate the total current number of distributors.
    5. Calculate total revenue (since inception) per city.
    6. Calculate total revenue (since inception) per distributor.
    7. Calculate total revenue (since inception) per location.
    8. Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work).
*/
import java.sql.*;
import java.util.Scanner;
public class OperationReports {
    // Finding the total proce of the copies of each of the publication.
    static void totalPrice(Scanner sc, Statement stmt) {
        String selectQuery = "Select O.p_id, D.d_id, MONTH(order_date) as Month, SUM(Copies) as Total_copies, SUM(Price*Copies) as Total_Price from `Order` O JOIN Distributor D ON O.d_id = D.d_id GROUP BY  D.d_id,MONTH(O.order_date) ,O.p_id";
        // try block for the Query.
        System.out.println("Number and total price of copies of each publication bought per distributor per month");
        try {
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print("Publicaition ID: " + rs.getInt("p_id"));
                System.out.print("\tDistributor ID: " + rs.getInt("d_id"));
                System.out.print("\tMonth: " + rs.getString("Month"));
                System.out.print("\tTotal Copies: " + rs.getString("Total_copies"));
                System.out.println("\tTotal Price: " + rs.getString("Total_Price"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }

    // Total Revenue of the Publication house.
    static void totalRevenue(Scanner sc, Statement stmt) {
        String selectQuery = "Select Sum(Copies*Price + Shipping_cost) as Total_Revenue From `Order`";
        // try block for the Query.
        try {
            System.out.println("Total revenue of the publishing house");
          
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.println("Total Revenue: " + rs.getFloat("Total_Revenue"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    //Total expenses (i.e., shipping costs and salaries) of the pubication house.
    static void totalExpenses(Scanner sc, Statement stmt) { 
        String selectQuery = "Select SUM(EXPENSE) as Total_Expense from (SELECT Sum(Shipping_cost) Expense from  `Order` Union SELECT Sum(paymentAmount) Expense from  Payments) a ";
        // try block for the Query.
        System.out.println("Total Expense of the publishing house");
        try {
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.println("Total Expense: " + rs.getFloat("Total_Expense"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    // Calculate the total current number of distributors.
    static void totalDistributors(Scanner sc, Statement stmt) {
        String selectQuery = "Select COUNT(d_id) as Total_Distributors from Distributor";
        // try block for the Query.
        try {
            System.out.println("Number of distributors of the publishing house");
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.println("Total Distributors: " + rs.getInt("Total_Distributors"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    // Calculate total revenue (since inception) per city.
    static void totalRevenueCity(Scanner sc, Statement stmt) {
        String selectQuery = "Select  D.city ,Sum(Copies*Price + Shipping_cost) as Total_Revenue from `Order` O JOIN Distributor D ON O.d_id = D.d_id GROUP BY D.city";
        // try block for the Query.
        try {
            System.out.println("Total Revenue Per City of the publishing house");
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print(" City: " + rs.getString("city"));
                System.out.println("\tTotal Revenue: " + rs.getFloat("Total_Revenue"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    // Calculate total revenue (since inception) per distributor.
    static void totalRevenueDistributor(Scanner sc, Statement stmt) {
        String selectQuery = "Select  D.d_id ,Sum(Copies*Price + Shipping_cost) as Total_Revenue from `Order` O JOIN Distributor D ON O.d_id = D.d_id GROUP BY D.d_id; ";
        // try block for the Query.
        try {
            System.out.println("Total Revenue Per Distributor of the publishing house");
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print(" Distributor ID : " + rs.getInt("d_id"));
                System.out.println("\tTotal Revenue: " + rs.getFloat("Total_Revenue"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    // Calculate total revenue (since inception) per location.
    static void totalRevenueAddress(Scanner sc, Statement stmt) {
        String selectQuery = "Select  D.address ,Sum(Copies*Price + Shipping_cost) as Total_Revenue from `Order` O JOIN Distributor D ON O.d_id = D.d_id GROUP BY D.address; ";
        // try block for the Query.
        try {
            System.out.println("Total Revenue Per Location of the publishing house");
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print(" Address : " + rs.getString("address"));
                System.out.println("\tTotal Revenue: " + rs.getFloat("Total_Revenue"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }   
    }
    // Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work).
    static void totalPayments(Scanner sc, Statement stmt) {
        String selectQuery = "Select start_date, end_date, work_type, sum(paymentAmount) Total_Payment from Payments P group by start_date,end_date,work_type";
        // try block for the Query.
        try {
            System.out.println("Total Payments of the publishing house by pay period and work type");
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                System.out.print(" Start Date : " + rs.getDate("start_date"));
                System.out.print("\tEnd Date: " + rs.getDate("end_date"));
                System.out.print("\tWork Type: " + rs.getString("work_type"));
                System.out.println("\tTotal Payment: " + rs.getFloat("Total_Payment"));
            }
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
