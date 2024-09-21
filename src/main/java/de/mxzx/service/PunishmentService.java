package de.mxzx.service;

import de.mxzx.model.PlayerData;
import de.mxzx.model.Punishment;
import lombok.Data;
import lombok.Getter;
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

@Data
@Getter
public class PunishmentService implements DataService<Punishment> {
    private final HibernateService service;

    @Override
    public boolean exists(String id) {
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            Object result = session.createCriteria(Punishment.class)
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
    public List<Punishment> loadAll() {
        List<Punishment> results = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Punishment> cr = cb.createQuery(Punishment.class);
            Root<Punishment> root = cr.from(Punishment.class);
            cr.select(root);
            Query<Punishment> query = session.createQuery(cr);
            results = query.getResultList();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return results != null ? results : new ArrayList<>();
    }

    @Override
    public Punishment load(String id) {
        Punishment type = null;
        try {
            Map<Session, Transaction> txMap = getService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            type = session.load(Punishment.class, id);
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return type;
    }

    @Override
    public void save(Punishment type) {
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
    public void update(Punishment type) {
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
