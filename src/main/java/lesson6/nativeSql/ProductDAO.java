package lesson6.nativeSql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    private final String FIND_BY_ID = "SELECT * FROM Product WHERE id = :id";
    private final String FIND_BY_NAME = "SELECT * FROM Product WHERE name = :name";
    private final String FIND_BY_CONTAINED_NAME = "SELECT * FROM Product WHERE name LIKE ?1";
    private final String FIND_BY_PRICE = "SELECT * FROM Product WHERE price BETWEEN  :start AND :finish";
    private final String FIND_BY_NAME_SORTED_ASC = "SELECT * FROM Product ORDER BY name ASC";
    private final String FIND_BY_NAME_SORTED_DESC = "SELECT * FROM Product ORDER BY name DESC";
    private final String FIND_BY_PRICE_SORTED_ASC = "SELECT * FROM Product WHERE price BETWEEN :start AND :finish ORDER BY price DESC";

    public Product findById(Long id) throws Exception {
        if (id <= 0) throw new NoSuchFieldException("Check your input id , it would be more than 0");

        Product res = null;

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(FIND_BY_ID);
            query.setParameter("id", id);
            query.addEntity(Product.class);
            res = (Product) query.getSingleResult();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }

        if (res == null) System.out.println("Cant find product with id " + id + " in DB ");

        return res;
    }

    public List<Product> findByName(String name) throws Exception {
        if (name == null) throw new NoSuchFieldException("Check your input name , it would not be null!");

        List<Product> res = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(FIND_BY_NAME);
            query.setParameter("name", name);
            query.addEntity(Product.class);
            res = query.list();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

        if (res.size() < 1) System.out.println("Cant find product with name " + name + " in DB ");

        return res;
    }

    public List<Product> findByContainedName(String name) throws Exception {
        if (name == null) throw new NoSuchFieldException("Check your input name , it would not be null!");

        List<Product> res = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(FIND_BY_CONTAINED_NAME);
            query.setParameter(1, "%" + name + "%");
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

        if (res.size() < 1) System.out.println("Cant find product with name like " + name + " in DB ");

        return res;
    }

    public List<Product> findByPrice(int price, int delta) throws Exception {
        if (price <= 0 || delta <= 0) throw new Exception("Check your input price and delta , they incorrect!");

        List<Product> res = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(FIND_BY_PRICE);
            query.setParameter("start", price - delta);
            query.setParameter("finish", price + delta);
            query.addEntity(Product.class);
            res = query.list();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

        if (res.size() < 1) System.out.println("Cant find product with price between this price in DB ");

        return res;
    }

    public List<Product> findByNameSortedAsc() {

        return sortedName(FIND_BY_NAME_SORTED_ASC);
    }

    public List<Product> findByNameSortedDesc() {

        return sortedName(FIND_BY_NAME_SORTED_DESC);
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws Exception {
        if (price <= 0 || delta <= 0) throw new Exception("Check your input price and delta , they incorrect!");

        List<Product> res = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(FIND_BY_PRICE_SORTED_ASC);
            query.setParameter("start", price - delta);
            query.setParameter("finish", price + delta);
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

        if (res.size() < 1) System.out.println("Cant find product with price between this price in DB ");

        return res;
    }

    private List<Product> sortedName(String SQLQuery) {

        List<Product> res = new ArrayList<>();

        try (Session session = createSessionFactory().openSession()) {

            NativeQuery query = session.createNativeQuery(SQLQuery);
            query.addEntity(Product.class);
            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

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

