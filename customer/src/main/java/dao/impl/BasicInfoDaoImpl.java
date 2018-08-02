package dao.impl;

import dao.BasicInfoDao;
import org.springframework.stereotype.Repository;
import pojo.BasicInfo;
import pojo.Food;
import pojo.Pager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
        BasicInfo basicInfo1 = manager.merge(basicInfo);
        return basicInfo1;
    }

    @Override
    public List<BasicInfo> findAllBasicInfo() {
        String spql = "from pojo.BasicInfo";
        List<BasicInfo> basicInfos = manager.createQuery(spql).getResultList();
        return basicInfos;
    }

    @Override
    public Pager findAllBasicInfo(int curPage, int pageSize) {
        String jpql = "FROM pojo.BasicInfo";
        Query query = manager.createQuery(jpql);
        List<BasicInfo> basicInfos = query.getResultList();
        int totalPage = basicInfos.size() / pageSize;
        int totalRow = basicInfos.size();
        query = manager.createQuery(jpql);
        basicInfos = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, basicInfos);
        return pager;
    }

    public BasicInfo findBasicInfoById(String id) {
        String jpql = "FROM pojo.BasicInfo where merchant.id=:id";

        BasicInfo basicInfo =(BasicInfo) manager.createQuery(jpql)
                .setParameter("id", id)
                .getSingleResult();

        return basicInfo;
    }
}
