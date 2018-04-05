package lesson6.hqlProducts;

public class Demo {
    public static void main(String[] args) throws Exception {


        ProductDAO productDAO = new ProductDAO();

        System.out.println( productDAO.findByPriceSortedDesc(5,10));
    }
}
