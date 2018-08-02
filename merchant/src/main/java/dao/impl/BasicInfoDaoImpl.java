package dao.impl;

import dao.BasicInfoDao;
import org.springframework.stereotype.Repository;
import pojo.BasicInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BasicInfoDaoImpl implements BasicInfoDao {
    @PersistenceContext(name = "em")
    private EntityManager manager;

    @Override
    public BasicInfo addBasicInfo(BasicInfo basicInfo) {
        manager.persist(basicInfo);
        return basicInfo;
    }

    @Override
    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        BasicInfo basicInfo1=manager.merge(basicInfo);
        return basicInfo1;
    }
}
