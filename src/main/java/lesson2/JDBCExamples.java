package lesson2;

import java.sql.*;

public class JDBCExamples {

    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";

    public static void main(String[] args) {


        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {


//            boolean res = statement.execute("INSERT INTO PRODUCT VALUES (2,'toy111','for children',100)");
//            System.out.println(res);

//            boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME = 'toy111'");
//            System.out.println(res);

         int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'car'");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
    }
}
