package containers;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JohnGao
 * @date 8/4/2018 1:36 PM
 */
@Component
public class SessionContainer implements Container{
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<String,WebSocketSession>();
    @Override
    public void addSession(String key,WebSocketSession session){
        sessionMap.put(key,session);
    }
    @Override
    public WebSocketSession getSession(String key){
        return sessionMap.get(key);
    }
    @Override
    public List<WebSocketSession> getAllSessions(){
        List<WebSocketSession> list = new ArrayList<WebSocketSession>();
        for (String key : sessionMap.keySet()){
            list.add(sessionMap.get(key));
        }
        return list;
    }
    @Override
    public int getSize(){
        return sessionMap.size();
    }

}
