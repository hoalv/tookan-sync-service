package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.RiderTookanAgent;
import shippo.sync.tookan.entity.TransportationTask;

import java.util.Iterator;
import java.util.List;

public class TransportationTaskManager {
    protected SessionFactory sessionFactory;

    public void setup() {
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

    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }


    public void readById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            TransportationTask task = (TransportationTask) session.get(TransportationTask.class, id);

            System.out.print("description: " + task.getDescription());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to CREATE an tookan_agents in the database */
    public Long addRiderTookanAgent(RiderTookanAgent agent) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Long agentId = null;

        try {
            tx = session.beginTransaction();
            agentId = (Long) session.save(agent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return agentId;
    }

    /* Method to UPDATE  for an tookan_agents */
    public void updateRiderTookanAgent(Integer id, RiderTookanAgent changedAgent) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RiderTookanAgent agent = (RiderTookanAgent) session.get(RiderTookanAgent.class, id);
            agent.setAgent(changedAgent.getAgent());
            agent.setAgentId(changedAgent.getAgentId());
            agent.setRiderId(changedAgent.getRiderId());
            agent.setUpdatedAt(changedAgent.getUpdatedAt());
            agent.setVersion(changedAgent.getVersion());
            session.update(agent);
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
        TransportationTaskManager manager = new TransportationTaskManager();
        manager.setup();

        manager.readById(97712);
//        manager.readById(4);
        manager.exit();
    }
}
