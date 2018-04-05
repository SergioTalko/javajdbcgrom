package lesson5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Demo {
    public static void main(String[] args) {
        Product product = new Product(1110, "test", "test", 77);
        save(product);

    }


    private static void save(Product product) {

        Session session = createSessionFactory().openSession();

        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();

        session.close();
    }

    private static SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

}
