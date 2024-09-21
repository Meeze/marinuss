package de.mxzx.service;

import de.mxzx.model.AMS;
import de.mxzx.model.Season;
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
public class SeasonService implements DataService<Season>{

    private final HibernateService service;

    @Override
    public boolean exists(String id) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            Object result = session.createCriteria(Season.class)
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
    public List<Season> loadAll() {
        List<Season> results = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Season> cr = cb.createQuery(Season.class);
            Root<Season> root = cr.from(Season.class);
            cr.select(root);
            Query<Season> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return results != null ? results : new ArrayList<>();
    }

    @Override
    public Season load(String id) {
        Season type = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            type = session.load(Season.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return type;
    }

    @Override
    public void save(Season type) {
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
    public void update(Season type) {
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
