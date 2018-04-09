package lesson6.nativeSql;

import javassist.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

            SQLQuery query = session.createSQLQuery("SELECT * FROM Product WHERE ID = :id ");
            query.setParameter("id", id);
            query.addEntity(Product.class);
            res = query.list();

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

    public List<Product> findByName(String name) throws Exception {
        if (name == null) throw new NoSuchFieldException("Check your input name , it would not be null!");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();


            SQLQuery query = session.createSQLQuery("SELECT * FROM Product WHERE NAME = :name ");
            query.setParameter("name", name);
            query.addEntity(Product.class);
            res = query.list();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with name " + name + " in DB ");

        return res;
    }

    public List<Product> findByContainedName(String name) throws Exception {
        if (name == null) throw new NoSuchFieldException("Check your input name , it would not be null!");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery("select * from Product where name like ?1 ");
            query.setParameter(1, "%" + name + "%");
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with name like " + name + " in DB ");

        return res;
    }

    public List<Product> findByPrice(int price, int delta) throws Exception {
        if (price <= 0 || delta <= 0) throw new Exception("Check your input price and delta , they incorrect!");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery("SELECT  * from Product a where a.price between :start and :finish ");
            query.setParameter("start" , price - delta);
            query.setParameter("finish" , price + delta);
            query.addEntity(Product.class);
            res = query.list();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with price between this price in DB ");

        return res;
    }

    public List<Product> findByNameSortedAsc(){

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery("SELECT * from Product  order by NAME asc ");
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        return res;
    }

    public List<Product> findByNameSortedDesc(){

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery("SELECT  * from Product  order by name desc ");
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        return res;
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws Exception{
        if (price <= 0 || delta <= 0) throw new Exception("Check your input price and delta , they incorrect!");

        Session session = null;
        List<Product> res = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery("SELECT * from Product a where a.price between :start and :finish order by price desc ");
            query.setParameter("start" , price - delta);
            query.setParameter("finish" , price + delta);
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        } finally {
            if (session != null) closeSessionFactory();
        }

        if (res.size() < 1) throw new NotFoundException("Cant find product with price between this price in DB ");

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

