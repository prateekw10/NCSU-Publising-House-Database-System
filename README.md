# How to Run

Compilation:
javac -cp ./mariadb-java-client-3.0.4.jar *.java

Execution:
java -cp ./mariadb-java-client-3.0.4.jar: login

Navigate through menu for the various operations

#  DOCUMENTATION 

The system has a main menu that gives the user the following operation options: “Editing and Publishing”, “Production of a book edition or of an issue of a publication”, “Distribution”, “Reports”, “Exit”. When prompted, the user enters a number 1-5 corresponding with what kind of operation they would like to execute. For each menu option there is a submenu displayed that has specific actions listed. Our system has a main class (login.java) that facilitates switching between the main menu options and separate classes for each related group of operations. This was done to break the application into more manageable and maintainable pieces of code. Additionally, this class forms a database connection which can be passed to the different options.

Our program also contains four helper classes OperationEditor.java, OperationDistribution.java, OperationProduction.java and OperationReports.java which are class that attempts to abstract the details of getting different types of input from the user, as well as a helper function that assists with constructing SQL query statements. We have constructed java classes to maintain the structure of each SQL table in the form of a java class.

OperationEditor.java

This java file lets the user Choose Editing and Publishing Operation Option: like 
 1) Enter Publication
 2) Update Publication Information
 3) Assign editor to Publication
 4) View Publication for a specific editor.
 5) Add Article
 6) Delete Article 
 7) Add chapter/section in a  Book
 8) Delete chapter/section in a  Book
 9) Exit to main menu

OperationProduction.java 

This java program lets the user Choose  Book Operation Option:"
1) Enter Book Edition
2) Enter Issue of Publication
3) Update Book Edition
4) Update Issue of Publication
5) Delete Book Edition or Publication Issue
6) Enter an article
7) Enter a chapter
8) Update an article
9) Update a chapter
10) Update text of an article
11) Find books
12) Find articles
13) Enter payment details
14) Claim payment
15) Exit to main menu

OperationDistribution.java

This function lets the user Choose Distribution Operation Option
1) Enter Distributor
2) Update Distributor Information
3) Delete a Distributor
4) Input Orders from a Distributor
5) Bill Distributor for an order and update outstanding balance
6) Exit to main menu

OperationReports.java

This java program  lets the user Choose Reports Operation Option:
1) Number and total price of copies of each publication bought per distributor per month"
2) Total revenue of the publishing house"
3) total expenses (i.e., shipping costs and salaries
4) Calculate the total current number of distributors
5) Calculate total revenue(since inception) per city.
6) Calculate total revenue (since inception) per distributor.
7) Calculate total revenue (since inception) per location.
8) Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work).
9) Exit to main menu

Publication.java 
This java program contains getter and setter methods for all the publication attributes like publication id, title, type, topic. Date. 

Distributor.java 
This java program contains getter and setter methods for all the distribution attributes like distribution id, address, contact number, name, city, type, balance, phone.   

Order.java
This java program contains getter and setter methods for all the order attributes like order id, shipping cost, type, number of copies, price, due date, order date, publication id. 

Payment.java 
This java program contains getter and setter methods for all the payment attributes like payment id, pay date, start date, claim date, end date, writer id, writer type(staff/guest). 

Writer.java
This java program contains getter and setter methods for all the Writer attributes like Writer name, writer id, type of the writer. 

Magazine_Journal.java 
This java program contains getter and setter methods for all the Magazine/Journal attributes like publication id, Issue number/ Article number, periodicity. 

Articles.java 
This java program contains getter and setter methods for all the article attributes like publication id, Article number, Article text, Article title.Article Date. 

Book.java 
This java program contains getter and setter methods for all the book attributes like publication id, edition, ISBN number. 

Chapter.java 
This java program contains getter and setter methods for all the Chapter attributes like publication id, Chapter number, Chapter title.Chapter Date. 

Writes_or_edits_articles.java 
This java program contains getter and setter methods for all the Writes_or_edits_articles attributes like publication id, Writer id, Article number. 

Writes_or_edits_chapters.java 
This java program contains getter and setter methods for all the Writes_or_edits_chapters attributes like publication id, Writer id, Chapter number. 
