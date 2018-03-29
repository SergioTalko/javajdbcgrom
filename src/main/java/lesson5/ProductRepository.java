package lesson5;

import javassist.NotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.zip.DataFormatException;

public class ProductRepository {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public void save(Product product) {
        checkProduct(product);
        Session session = sessionFactory.openSession();

        try {
            session.getTransaction().begin();
            session.save(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Product with id " + product.getId() + " cant save in DB");
            e.printStackTrace();
        } finally {
            closeSessionFactory();
        }


    }

    public void delete(long id) throws Exception {
        if (id <= 0) throw new DataFormatException("This id " + id + " not allowed here");


        Session session = sessionFactory.openSession();
        Product result = null;

        try {
            session.getTransaction().begin();
            result = session.get(Product.class, id);
            Hibernate.initialize(result);
            if (result == null) throw new NotFoundException("Product with id " + id + " not found in DB");
            session.delete(result);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            closeSessionFactory();
        }

    }

    public void update(Product product) {
        checkProduct(product);
        Session session = sessionFactory.openSession();

        try {
            session.getTransaction().begin();
            session.update(product);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.err.println("Product with id " + product.getId() + " cant save in DB");
            e.printStackTrace();
        } finally {
            if (session.isConnected()) closeSessionFactory();
        }
    }


    private void checkProduct(Product product) {
        if (product == null || product.getId() <= 0 || product.getName() == null || product.getDescription() == null || product.getPrice() <= 0)
            throw new NullPointerException("Input data is wrong");
    }

    private void closeSessionFactory() {
        sessionFactory.close();
    }
}
