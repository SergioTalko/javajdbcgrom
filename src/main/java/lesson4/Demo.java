package lesson4;

import lesson3.Product;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        TransactionDemo transactionDemo = new TransactionDemo();

        Product product = new Product(78, "fhjk", "hjk", 100);
        Product product2 = new Product(78,"fhjk","hjk",100);
        Product product3 = new Product(78, "fhjk", "hjk", 100);

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);

        transactionDemo.save(products);
    }
}
