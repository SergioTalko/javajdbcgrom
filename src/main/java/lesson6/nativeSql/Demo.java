package lesson6.nativeSql;

import org.hibernate.SQLQuery;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findById((long)50));
//        System.out.println(productDAO.findByName("change3"));
//        System.out.println(productDAO.findByContainedName("change3"));
//        System.out.println(productDAO.findByNameSortedAsc());
//        System.out.println(productDAO.findByPrice(5,5));

    }
}
