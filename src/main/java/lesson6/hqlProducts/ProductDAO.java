package lesson6.hqlProducts;

import javassist.NotFoundException;
import lesson6.task1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    public Product findById(Long id) throws Exception {
        if (id <= 0) throw new NoSuchFieldException("Check your input id , it would be more than 0");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            Query query = session.createQuery("from Product where id = :id");
            query.setParameter("id", id);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with id " + id + " in DB ");
        if (res.size() > 1) throw new Exception("In DB more than 1 products with id " + id);
        return res.get(0);
    }

    public List<Product> findByName(String name) throws Exception{
        if (name == null) throw new NoSuchFieldException("Check your input name , it would not be null!");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            Query query = session.createQuery("from Product where name = :name");
            query.setParameter("name", name);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with name " + name + " in DB ");

        return res;
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

    private void closeSessionFactory() {
        sessionFactory.close();
    }
}
