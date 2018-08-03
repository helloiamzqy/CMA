package containers.impl;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import containers.WebSocketSessionContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * chat session container
 * 
 * @author will
 *
 */
@Component("syncContainer")
public class DefaultSyncSessionContainer implements WebSocketSessionContainer {

	private static final Map<String, WebSocketSession> seesionMaps= 
			new HashMap<String, WebSocketSession>();

	
	public void add(WebSocketSession bean,String id) {
		seesionMaps.put(id, bean);
	}

	public WebSocketSession get(String id) {
		return seesionMaps.get(id);
	}

	public void remove(String id) {
		seesionMaps.remove(id);
	}

	public Collection<String> getKeys() {
		return seesionMaps.keySet();
	}

	public int size() {
		return seesionMaps.size();
	}
}
