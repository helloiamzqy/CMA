package dao.impl;

import dao.RegisterInfoDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pojo.Merchant;
import pojo.Pager;
import pojo.RegisterInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Dunn
 */
@Repository
public class RegistInfoDaoImpl implements RegisterInfoDao {
    @PersistenceContext(name="em")
    private EntityManager manager ;

    @Override
    public RegisterInfo getRegisterInfo(Merchant merchant) {
        String jpql = "FROM pojo.RegisterInfo r WHERE r.merchant = :merchant";
        Query query = manager.createQuery(jpql).setParameter("merchant",merchant);
        RegisterInfo registerInfo = (RegisterInfo) query.getSingleResult();
        return registerInfo;
    }
    @Override
    public RegisterInfo addRegisterInfo(RegisterInfo registerInfo) {
         manager.persist(registerInfo);
         return registerInfo;
    }


}
