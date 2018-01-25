package lesson2;


import org.apache.commons.lang3.StringUtils;

import java.sql.*;


public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";


    public ResultSet getAllProducts() {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM PRODUCT");

        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProductsByPrice() {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE <= 100");

        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getProductsByDescription() {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH (DESCRIPTION) > 50");

        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
        return resultSet;
    }

    public void saveProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)");
        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
    }

    public int deleteProducts() {
        int resultSet = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME != 'toy'");
        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
        return resultSet;
    }

    public void increasePrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("UPDATE PRODUCT SET PRICE = PRICE + 100  WHERE PRICE < 970");
        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
    }

    public void changeDescription() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION)>100");
            while (resultSet.next()) {
                String description = resultSet.getString(3);
                //if last char is '.' - delete '.'
                if (description.charAt(description.length() - 1) == '.') {
                    description = StringUtils.chop(description);
                }
                //delete last sentence
                String deleteLast = description.substring(0, description.lastIndexOf("."));

                long id = resultSet.getLong(1);


                String updateSQL = ("UPDATE PRODUCT SET DESCRIPTION = ? WHERE ID = ?");
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
                preparedStatement.setString(1, deleteLast);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println("Smth going wrong");
            e.printStackTrace();
        }
    }

}
