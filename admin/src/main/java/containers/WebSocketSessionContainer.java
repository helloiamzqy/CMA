package containers;

import org.springframework.web.socket.WebSocketSession;


public interface WebSocketSessionContainer extends Container<WebSocketSession,String>{

    int size();

    
}
