package de.mxzx.service;

import de.mxzx.model.PlayerData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class PlayerDataService implements DataService<PlayerData> {

    private final HibernateService service;

    @Override
    public boolean exists(String id) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            Object result = session.createCriteria(PlayerData.class)
                    .add(Restrictions.eq("uuid", id))
                    .setProjection(Projections.property("uuid"))
                    .uniqueResult();
            tx.commit();
            session.close();
            return result != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<PlayerData> loadAll() {
        List<PlayerData> results = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<PlayerData> cr = cb.createQuery(PlayerData.class);
            Root<PlayerData> root = cr.from(PlayerData.class);
            cr.select(root);
            Query<PlayerData> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return results != null ? results : new ArrayList<>();
    }

    @Override
    public PlayerData load(String id) {
        PlayerData type = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            type = session.load(PlayerData.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return type;
    }

    @Override
    public void save(PlayerData type) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            session.saveOrUpdate(type);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void update(PlayerData type) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            session.saveOrUpdate(type);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(String id) {

    }
}
