package service.impl;


import org.springframework.stereotype.Component;
import service.UserService;
import com.google.gson.Gson;
import jmsmananger.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private JmsSender sender;
    @Override
    public void addUser(User user) {
        Gson gson = new Gson();
        sender.send(gson.toJson(user));
    }
}
