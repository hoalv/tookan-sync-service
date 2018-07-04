package shippo.sync.tookan.entitymanager;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.RiderTookanAgent;

import java.util.Iterator;
import java.util.List;

public class RiderTookanAgentManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
        // code to load Hibernate Session factory
        // final StandardServiceRegistry registry = new
        // StandardServiceRegistryBuilder()
        // .configure() // configures settings from hibernate.cfg.xml
        // .build();
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }



    protected void readAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM RiderTookanAgent").list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext();) {
                RiderTookanAgent riderTookanAgent = (RiderTookanAgent) iterator.next();
                System.out.print("rider_id: " + riderTookanAgent.getRiderId());
                System.out.print("agent: " + riderTookanAgent.getAgent());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    protected void readByRider(int riderId) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM RiderTookanAgent T WHERE T.riderId = " + riderId).list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext();) {
                RiderTookanAgent riderTookanAgent = (RiderTookanAgent) iterator.next();
                System.out.print("rider_id: " + riderTookanAgent.getRiderId());
                System.out.print("agent: " + riderTookanAgent.getAgent());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    protected void readById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            RiderTookanAgent riderTookanAgent = (RiderTookanAgent) session.get(RiderTookanAgent.class, id);

            System.out.print("rider_id: " + riderTookanAgent.getRiderId());
            System.out.print("agent: " + riderTookanAgent.getAgent());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected void delete(long taskId) {
        // code to remove a task
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            RiderTookanAgent task = (RiderTookanAgent) session.get(RiderTookanAgent.class, taskId);
            session.delete(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public static void main(String[] args) {
        // code to run the program
        RiderTookanAgentManager manager = new RiderTookanAgentManager();
        manager.setup();
        manager.readByRider(1);
        manager.exit();
    }
}
