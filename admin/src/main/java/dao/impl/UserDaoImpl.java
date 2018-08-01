package dao.impl;

import dao.UserDao;
import org.springframework.stereotype.Repository;
import pojo.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    public User insertUser(User user) {
        manager.persist(user);
        return user;
    }
}
