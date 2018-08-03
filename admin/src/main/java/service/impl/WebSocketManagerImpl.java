package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import containers.WebSocketSessionContainer;
import service.WebSocketManager;

import java.io.IOException;


/**
 * @author JohnGao
 * @date 8/3/2018 3:58 PM
 */
@Component("WebSocketManager")
public class WebSocketManagerImpl implements WebSocketManager {
    @Autowired
    @Qualifier("syncContainer")
    private WebSocketSessionContainer container;
    @Override
    public void sendMessage(String testId){
        System.out.println("在发送信息前，container的大小为：" + container.size());
        WebSocketSession session = container.get("111111");
        try {
            session.sendMessage(new TextMessage("收到了"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("在发送信息后，container的大小为：" +container.size());
        System.out.println("发送信息后的session为："+container.get("111111"));
    }
}
