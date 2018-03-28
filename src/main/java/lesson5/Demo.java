package lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Product product = new Product(88,"test","test",77);
        save(product);

    }


    private static void save(Product product){
        Session session = new HibernateUtils().createSessionFactory().openSession();

        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();

        System.out.println("Done");
        session.close();
    }
}
