package lesson6.nativeSql;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findById((long)50));
    }
}
