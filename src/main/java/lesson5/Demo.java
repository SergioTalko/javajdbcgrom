package lesson5;

import org.hibernate.Session;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Demo {
    public static void main(String[] args) throws JAXBException {
        Session session = new HibernateUtils().createSessionFactory().openSession();

        session.getTransaction().begin();

        Product product = new Product();
        product.setId(99);
        product.setName("toy");
        product.setDescription("test");
        product.setPrice(499);
        session.save(product);

        session.getTransaction().commit();

        System.out.println("Done");
        session.close();

    }
}
