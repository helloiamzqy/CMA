package service.impl;

import containers.Container;
import containers.SessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import service.WebSocketManager;

import java.io.IOException;


/**
 * @author JohnGao
 * @date 8/3/2018 3:58 PM
 */
@Component
public class WebSocketManagerImpl implements WebSocketManager {

    @Autowired
    private Container container;
    @Override
    public void sendMessage(String key,String data) throws Exception{
        for (WebSocketSession session:container.getAllSessions()){
            if (session.isOpen()){
                session.sendMessage(new TextMessage(data));
            }
        }
    }
}
