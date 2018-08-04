package dao.impl;

import dao.ManagerDao;
import org.springframework.stereotype.Repository;
import pojo.Manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author: Carrie
 * @date: 2018-8-3 17:21
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @PersistenceContext(name="em")
    private EntityManager em;

    @Override
    public Manager findManager(Manager manager) {
        String jpql = "from pojo.Manager where name=?1 and password=?2";
        Query query = em.createQuery(jpql);
        query.setParameter(1,manager.getName());
        query.setParameter(2,manager.getPassword());
        List<Manager> list = query.getResultList();
        Manager manager1 = null;
        if(list.size()>0){
            manager1 = list.get(0);
        }
        return manager1;
    }
}
