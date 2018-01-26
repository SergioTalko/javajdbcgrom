package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {


    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";


    public Product save(Product product) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?,?,?,?)")) {

            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());
            int res = statement.executeUpdate();
            System.out.println("Saving was finished with result " + res);
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCT SET ?,?,? WHERE  ID = ?")) {

            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());
            int res = statement.executeUpdate();
            System.out.println("Update was finished with result " + res);
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public void delete(long id) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID = ?")) {
            statement.setLong(1, id);
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
            List<Product> list = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                list.add(product);
            }

            return list;
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
