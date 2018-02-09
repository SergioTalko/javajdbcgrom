package lesson4;

import lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {


    //1.save order- pay money- receive money
    //2.save order- pay money- receive money


    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";


    public void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);
        } catch (SQLException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }
    }

    private void saveList(List<Product> products, Connection connection) throws SQLException {

        long tempId = 0;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?,?,?,?)")) {
            connection.setAutoCommit(false);

            for (Product product : products) {
                statement.setLong(1, product.getId());
                tempId = product.getId();
                statement.setString(2, product.getName());
                statement.setString(3, product.getDescription());
                statement.setInt(4, product.getPrice());
                statement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Cant save product with id : " + tempId);
            throw e;

        }

    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
