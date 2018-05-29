package lesson8.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;


public class GeneralDAO<T> {

    private SessionFactory sessionFactory;
    private String tableName;



    public void save(T t) {

        Transaction transaction = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
    }


    public void update(T t) {

        Transaction transaction = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.merge(t);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
    }



    public void delete(long id, String sql) {
        Transaction transaction = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.delete(sql);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
    }


    @SuppressWarnings("unchecked")
    public T findById(long id, String sql) {

        T res = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {


            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            res = (T) query.getSingleResult();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }

        if (res == null) System.out.println("Cant find product with id " + id + " in DB ");
        return res;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {

        List<T> res = new LinkedList<>();

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {


            Query query = session.createQuery(getAll(tableName));

            res = query.list();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }

        return res;
    }


    private String getAll(String tableName){
        return "FROM " + tableName;
    }

    public List<T> selectByOneParameter(Object obj1, String SQL) {

        List<T> res = new ArrayList();

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Query query = session.createQuery(SQL);
            query.setParameter("param", obj1);

            res = query.getResultList();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();

        }

        return res;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
