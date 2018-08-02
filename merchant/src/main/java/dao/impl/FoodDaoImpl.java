package dao.impl;

import dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Dunn
 */
@Repository
public class FoodDaoImpl implements FoodDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public Pager findAllFood(int curPage, int pageSize) {
        String jpql ="FROM pojo.Food";
        Query query = manager.createQuery(jpql);
        List<Food> foods = query.getResultList();
        int totalPage = foods.size() / pageSize;
        int totalRow = foods.size();
        query = manager.createQuery(jpql);
        foods = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, foods);
        return pager;

    }

    @Override
    public Food addFood(Food food) {
        manager.persist(food);
        return food;
    }

    @Override
    public void deleteFood(String id) {
        Food food = manager.getReference(Food.class,id);
        food.setStatus("0");
        manager.merge(food);
    }

    @Override
    public Food updateFood(Food food) {
        Food newFood = manager.merge(food);
        return newFood;
    }

    @Override
    public Pager findFoodByMerchant(int curPage, int pageSize,Merchant merchant) {
        String jpql ="FROM pojo.Food it WHERE it.merchant =:merchant";
        Query query = manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        List<Food> foods = query.getResultList();
        int totalPage = foods.size() / pageSize;
        int totalRow = foods.size();
        query = manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        foods = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, foods);
        return pager;
    }

    @Override
    public Pager findFoodByName(int curPage, int pageSize,String foodName) {
        String jpql = "FROM pojo.Food f WHERE f.foodName = :foodName ";
        Query query = manager.createQuery(jpql);
        query.setParameter("foodName",foodName);
        List<Food> foods = query.getResultList();
        int totalPage = foods.size() / pageSize;
        int totalRow = foods.size();
        query = manager.createQuery(jpql);
        query.setParameter("foodName",foodName);
        foods = query.setFirstResult((curPage - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, foods);
        return pager;
    }
}
