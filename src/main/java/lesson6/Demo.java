package lesson6;

public class Demo {
    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        product.setId(18);
        product.setName("change");
        product.setDescription("test");
        product.setPrice(100);

        productDAO.update(product);
    }




}
