package jersey;

import com.google.gson.Gson;
import org.glassfish.jersey.client.JerseyClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Component("serverInteraction")
public class ServerInteraction {
    @Resource(name = "jerseyPoolingClient")
    JerseyClient client;

    public String interact(String destination, Object msg) {
        //WebTarget

        String result = null;
        try {
            WebTarget target = client.target(destination);
            //Response
            Response response = target.request().get();
            result = response.readEntity(String.class);
            System.out.println("result:" + result);

            response.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//
//	public String toJson(String uri,Object object) {
//		String msg = new Gson().toJson(object);
//		if(msg.equals("null")) {
//			msg = "";
//		}
//		String result = uri+Config.SERVER_PATH_TEST+msg;
//		System.out.println("result:::"+result);
//		return result;
//	}

}
