package lesson6;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;


    public Product save(Product product) {

        checkProduct(product);
        Session session = null;
        Transaction transaction = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Product with name " + product.getName() + " cant save in DB");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) closeSessionFactory();
        }
        return product;
    }

    public Product update(Product product) {

        checkProduct(product);
        Session session = null;
        Transaction transaction = null;


        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(product);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Product with name " + product.getName() + " cant update");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) closeSessionFactory();
        }
        return product;
    }

    public Product delete(Product product) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(product);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Product with id " + product.getId() + " cant be delete");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        } finally {
            if (session != null) closeSessionFactory();
        }
        return product;
    }

    public void saveAll(List<Product> products) {

    }

    public void updateAll(List<Product> products) {

    }

    public void deleteAll(List<Product> products) {

    }


    /*private Product findProductById(long id) {

        Session session = null;
        Product result = null;


        try {
            session = createSessionFactory().openSession();
            result = session.get(Product.class, id);

        } catch (HibernateException e) {
            System.err.println("Product with id " + id + " cant find");
            e.printStackTrace();
        } finally {
            if (session != null) closeSessionFactory();
        }

        return result;
    }*/


    private void closeSessionFactory() {
        sessionFactory.close();
    }

    private void checkProduct(Product product) {
        if (product == null || product.getName() == null || product.getDescription() == null || product.getPrice() <= 0)
            throw new NullPointerException("Input data is wrong");
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


}
