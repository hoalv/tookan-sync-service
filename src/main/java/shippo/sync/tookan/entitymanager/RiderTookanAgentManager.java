package shippo.sync.tookan.entitymanager;

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

    public RiderTookanAgent readByAgent(String agent) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        RiderTookanAgent riderTookanAgent = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM RiderTookanAgent T WHERE T.agent = '" + agent + "'").list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext(); ) {
                riderTookanAgent = (RiderTookanAgent) iterator.next();
                System.out.println("rider_id: " + riderTookanAgent.getRiderId());
                System.out.println("agent: " + riderTookanAgent.getAgent());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return riderTookanAgent;
    }

    public RiderTookanAgent readByRiderId(int rider_id) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        RiderTookanAgent riderTookanAgent = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM RiderTookanAgent T WHERE T.riderId =" + rider_id ).list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext(); ) {
                riderTookanAgent = (RiderTookanAgent) iterator.next();
                System.out.println("agent: " + riderTookanAgent.getAgent());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return riderTookanAgent;
    }

    public void readById(long id) {
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
        RiderTookanAgentManager manager = new RiderTookanAgentManager();
        manager.setup();
////        manager.readByRider(1);
//
//        RiderTookanAgent agent = new RiderTookanAgent();
////        agent.setId(3);
//        agent.setAgent("leviethoa");
//        agent.setAgentId(31);
//        agent.setRiderId(11);
////        agent.setUpdatedAt(changedAgent.getUpdatedAt());
//        agent.setVersion(0);
//
////        int a = manager.addRiderTookanAgent(agent);
////        System.out.println("OK: " + a);
//        manager.updateRiderTookanAgent(4, agent);
//        System.out.println("OK!");
        RiderTookanAgent rider = manager.readByRiderId(7);
//        manager.readById(4);
        manager.exit();
    }
}
