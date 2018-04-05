package lesson6.task1;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        product.setId(46);
        product.setName("change1");
        product.setDescription("test");
        product.setPrice(300);

        Product product2 = new Product();
        product2.setId(48);
        product2.setName("change2");
        product2.setDescription("test");
        product2.setPrice(300);

        Product product3 = new Product();
        product3.setId(50);
        product3.setName("change3");
        product3.setDescription("test");
        product3.setPrice(300);

        List<Product> products = Arrays.asList(product,product2,product3);

//        productDAO.saveAll(products);
//        productDAO.updateAll(products);
        productDAO.deleteAll(products);

    }
}
