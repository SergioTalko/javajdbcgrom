package lesson3;

public class Demo {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(100, "test", "dsr", 99);
        System.out.println(productDAO.save(product));
//   System.out.println( productDAO.getProducts());

        Solution solution = new Solution();

      // System.out.println(solution.findProductsByName("HeLLo"));

     //   System.out.println(solution.findProductsByPrice(100,10));

//        System.out.println(solution.findProductsWithEmptyDescription());
    }
}
