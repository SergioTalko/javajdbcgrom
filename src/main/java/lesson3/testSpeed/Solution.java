package lesson3.testSpeed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";





    public long testSavePerformance(){
        long start = new Date().getTime();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES (?, ?, ?)")) {
            for (int i = 1; i<50001; i++) {
                statement.setLong(1, i);
                statement.setString(2, "some information");
                statement.setInt(3, i);
                int res = statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return new Date().getTime() - start; // 1687299 ms
    }

    public long testDeleteByIdPerformance(){
        long start = new Date().getTime();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?")) {
            for (int i = 1; i<50001; i++) {
                statement.setLong(1, i);
                int res = statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return new Date().getTime() - start; // 1599193 ms
    }

    public long testDeletePerformance(){
        long start = new Date().getTime();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM TEST_SPEED")) {
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return new Date().getTime() - start;
    }

    public long testSelectByIdPerformance(){
        long start = new Date().getTime();
        for (int i = 1; i<50001; i++) {

            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM TEST_SPEED WHERE ID = ?")) {
                statement.setLong(1, i);
                int res = statement.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Smth went wrong");
                e.printStackTrace();
            }
        }
        return new Date().getTime() - start;
    }

    public long testSelectPerformance(){
        long start = new Date().getTime();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TEST_SPEED")) {
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return new Date().getTime() - start;
    }

    private Connection getConnection()throws SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
