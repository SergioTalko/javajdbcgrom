package lesson7.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


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

    public void delete(long id){
        Transaction transaction = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
    }


    public T findById(long id){

        T res = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {


            Query query = session.createQuery(getQueryFindById(tableName));
            query.setParameter("id", id);
            res = (T) query.getSingleResult();

        } catch (HibernateException e) {
            System.err.println("Smth went wrong");
            e.printStackTrace();
        }

        if (res == null) System.out.println("Cant find product with id " + id + " in DB ");
        return res;
    }

    private String getQueryFindById(String tableName) {
        return "FROM " + tableName + " WHERE id = :id ";
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
