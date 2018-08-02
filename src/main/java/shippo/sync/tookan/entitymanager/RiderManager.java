package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.v0.Rider;
import shippo.sync.tookan.entity.v0.RiderTookanAgent;

import java.util.Iterator;
import java.util.List;

public class RiderManager {
    protected SessionFactory sessionFactory;

    public void setup() {
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

    public Rider getRiderById(long id) {
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

    public Rider getRiderByUserId(long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Rider rider = null;
        try {
            tx = session.beginTransaction();

            List riders = (List) session.createQuery("FROM Rider T WHERE T.userId =" + userId ).list();
            for (Iterator iterator = riders.iterator(); iterator.hasNext(); ) {
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

    public void updateIsSyncTookan(Long id, Boolean isSyncTookan) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Rider rider = (Rider) session.get(Rider.class, id);
            rider.setIsSyncedTookan(isSyncTookan);
            session.update(rider);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        // code to run the program
        RiderManager manager = new RiderManager();
        manager.setup();
//        Rider rider = manager.getRiderById(1239);
        manager.updateIsSyncTookan((long) 1239, false);
//        System.out.println(rider.getEmail());
        manager.exit();
    }
}
