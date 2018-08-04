package containers;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/4/2018 1:57 PM
 */
public interface Container {
    public void addSession(String key, WebSocketSession session);
    public WebSocketSession getSession(String key);
    public List<WebSocketSession> getAllSessions();
    public int getSize();
}
