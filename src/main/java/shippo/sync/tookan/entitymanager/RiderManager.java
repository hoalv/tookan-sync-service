package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.v0.Rider;

import java.util.Iterator;
import java.util.List;

public class RiderManager {
    protected SessionFactory sessionFactory;

    public void setup() {
        // code to load Hibernate Session factory
        // final StandardServiceRegistry registry = new
        // StandardServiceRegistryBuilder()
        // .configure() // configures settings from hibernate_rider_service.cfg.xml
        // .build();
        try {
            sessionFactory = new Configuration().configure("hibernate_rider_service.cfg.xml").buildSessionFactory();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    public Rider getRiderById(int id) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Rider rider = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM Rider T WHERE T.id =" + id ).list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext(); ) {
                rider = (Rider) iterator.next();
                System.out.println("email: " + rider.getEmail());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rider;
    }

    public Rider getRiderByUserId(int id) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Rider rider = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM Rider T WHERE T.user_id =" + id ).list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext(); ) {
                rider = (Rider) iterator.next();
                System.out.println("email: " + rider.getEmail());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rider;
    }

//    public Rider getRiderById(long id) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = null;
//        Rider rider = null;
//        try {
//            tx = session.beginTransaction();
//            rider = (Rider) session.get(Rider.class, id);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return rider;
//    }

    public static void main(String[] args) {
        // code to run the program
        RiderManager manager = new RiderManager();
        manager.setup();
        Rider rider = manager.getRiderById(660);
        System.out.println(rider.getEmail());
        manager.exit();
    }
}
