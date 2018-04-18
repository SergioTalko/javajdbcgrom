package lesson6.hqlProducts;

public class Demo {
    public static void main(String[] args) throws Exception {


        ProductDAO productDAO = new ProductDAO();

//        System.out.println(productDAO.findByName("change3"));
//        System.out.println(productDAO.findByPrice(5,10));
//        System.out.println(productDAO.findByNameSortedDesc());
//        System.out.println(productDAO.findByNameSortedAsc());
        System.out.println( productDAO.findByPriceSortedDesc(5,10));
    }
}
