package handler;

import containers.Container;
import containers.SessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author JohnGao
 * @date 8/3/2018 2:34 PM
 */
public class MessageHandler extends TextWebSocketHandler{

    @Autowired
    private Container container;

    public MessageHandler() {
        super();
        System.out.println("MessageHandler : init");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("-------创建sockets连接-------");

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        System.out.println("获得前端发送过来的消息 : " + message.getPayload());
        container.addSession(message.getPayload(), session);
        System.out.println("此时储存session，container大小为 : " + container.getSize());
        if (container.getSize() > 0) {
            System.out.println("被存储的session为： " + container.getSession(message.getPayload()));
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("--------结束sockets连接---------");
    }

}
