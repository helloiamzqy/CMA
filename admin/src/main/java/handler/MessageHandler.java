package handler;

import containers.WebSocketSessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("syncContainer")
    private WebSocketSessionContainer container;

    public MessageHandler() {
        super();
        System.out.println("MessageHandler...init");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("创建sockets连接");

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            System.out.println("获得前端发送过来的消息");
            System.out.println(message.getPayload());
            container.add(session,"111111");
            System.out.println("此时储存session，container大小为 " + container.size());
            if (container.size()>0){
                System.out.println("不为0时，存储的session为： "+container.get("111111"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("结束sockets连接");
    }

}
