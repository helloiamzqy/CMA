package service;

/**
 * @author JohnGao
 * @date 8/3/2018 3:57 PM
 */
public interface WebSocketManager {
    public void sendMessage(String key,String data) throws Exception;
}
