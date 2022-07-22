package jm.task.core.jdbc.dao;



import org.hibernate.Session;
import org.hibernate.Transaction;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
//    private SessionFactory sessionFactory; //= Util.getConnection();
    private Session session;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS test.users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public void dropUsersTable() {
//        Session session = sessionFactory.openSession();
        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("Drop table if exists test.users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        Session session = sessionFactory.openSession();
        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
//            Session session1 = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            //session.flush();
            //session.getTransaction().commit();
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
//        Session session = sessionFactory.openSession();
        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User удален");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        //Session session = sessionFactory.openSession();
        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
//        Session session = sessionFactory.openSession();
        session = Util.getConnection().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
//            final List<User> instances = session.createCriteria(User.class).list();
            session.createNativeQuery("TRUNCATE TABLE test.users;").executeUpdate();
//            for (Object o : instances) {
//                session.delete(o);
//            }

            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }
}