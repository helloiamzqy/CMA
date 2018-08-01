package dao.impl;

import dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojo.Food;
import pojo.Merchant;

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
    public List<Food> findAllFood() {
        String jpql ="FROM pojo.Food";
        List<Food> foods = manager
                .createQuery(jpql)
                .getResultList();
        return foods;

    }

    @Override
    public Food addFood(Food food) {
        manager.persist(food);
        return food;
    }

    @Override
    public void deleteFood(String id) {
        Food food = manager.getReference(Food.class,id);
        manager.remove(food);
    }

    @Override
    public Food updateFood(Food food) {
        Food newFood = manager.merge(food);
        return newFood;
    }

    @Override
    public List<Food> findFoodByMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public List<Food> findFoodByName(String food) {
        String jpql = "FROM pojo.Food f WHERE f.foodName = :foodName ";
        Query query = manager.createQuery(jpql);
        List<Food> foods = query.getResultList();
        return foods;
    }
}
