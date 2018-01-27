package lesson3;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";


    public ArrayList<Product> findProductsByPrice(int price, int delta) {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE >= ? AND PRICE <=?")) {
            statement.setInt(1, price - delta);
            statement.setInt(2, price + delta);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> findProductsByName(String word) throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        validationWord(word); //validation word

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE  UPPER(NAME) LIKE UPPER(?)")) {

            statement.setString(1, "%" + word.toUpperCase() + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Product> findProductsWithEmptyDescription() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return products;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void validationWord(String word) throws Exception {
        if (word.length() < 3) throw new Exception("Word must contain more than 3 symbol.You word is  " + word);

        for (char ch : word.toCharArray()) {
            if (!Character.isLetter(ch)) {
                throw new Exception("Word can contains only letter.You word is " + word);
            }
        }


    }
}
