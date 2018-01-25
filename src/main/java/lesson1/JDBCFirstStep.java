package lesson1;

import java.sql.*;

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";

    public static ResultSet jdbcStart() {

        ResultSet result = null;


        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            //1. DB connection
            //2.create connection
            //3.create query/statement
            //4.execute query
            //5.work with result
            //6.close all the connections
            //

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.err.println("Class " + JDBC_DRIVER + " not found");
            }

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS")) {
                result = resultSet;

            }

        } catch (SQLException exception) {
            System.err.println("Smth going wrong");
            exception.printStackTrace();
        }
        return result;
    }
}