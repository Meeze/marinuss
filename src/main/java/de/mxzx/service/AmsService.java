package de.mxzx.service;

import de.mxzx.model.AMS;
import de.mxzx.model.Mine;
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
public class AmsService implements DataService<AMS>{

    private final HibernateService service;

    @Override
    public boolean exists(String id) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            Object result = session.createCriteria(AMS.class)
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
    public List<AMS> loadAll() {
        List<AMS> results = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<AMS> cr = cb.createQuery(AMS.class);
            Root<AMS> root = cr.from(AMS.class);
            cr.select(root);
            Query<AMS> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return results != null ? results : new ArrayList<>();
    }

    @Override
    public AMS load(String id) {
        AMS type = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            type = session.load(AMS.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return type;
    }

    @Override
    public void save(AMS type) {
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
    public void update(AMS type) {
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