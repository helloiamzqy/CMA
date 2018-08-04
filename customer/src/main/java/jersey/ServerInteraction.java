//package jersey;
//
//import com.alibaba.fastjson.JSON;
//import consts.TransJson;
//import org.glassfish.jersey.client.JerseyClient;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//
//@Component("serverInteraction")
//public class ServerInteraction {
//	@Resource(name="jerseyPoolingClient")
//	JerseyClient client;
//
//	public void interact(String destination,TransJson msg) {
//		//WebTarget
//		try {
//			WebTarget target = client.target(toJson(destination,msg));
//			//Response
//			Response response = target.request().get();
//			String result = response.readEntity(String.class);
//			System.out.println("result:"+result);
//			response.close();
//			client.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public String toJson(String uri,TransJson object) {
//		String msg = JSON.toJSONString(object);
//		if(msg.equals("null")) {
//			msg = "";
//		}
//		String result = uri+Config.SERVER_PATH_TEST+msg;
//		System.out.println("result:::"+result);
//		return result;
//	}
//
//}
