package service.impl;

import com.google.gson.Gson;
import dto.AdvertisementDto;
import dto.RegisterInfoDto;
import pojo.JmsMessage;

/**
 * @author Dunn
 */
public class jsonTest {
    public static void main(String[] args){
        Gson gson = new Gson();
        JmsMessage jsm = gson.fromJson("{\n" +
                "    \"jmsEnum\":\"APPLY\",\n" +
                "    \"object\":{\n" +
                "        \"id\":\"d8b85e6c-3690-4df3-84a9-be1fd79c31ec\",\n" +
                "        \"merchantId\":\"8a5e9d3c64fa99be0164fa99c4f00000\",\n" +
                "        \"creditCode\":\"441522\",\n" +
                "        \"idCard\":\"asddddddddd\",\n" +
                "        \"corporateName\":\"dunn\",\n" +
                "        \"picture\":\"asdddddd\",\n" +
                "        \"phone\":\"123123123\",\n" +
                "        \"shopName\":\"kfc\",\n" +
                "        \"address\":\"南方软件园\",\n" +
                "        \"status\":\"0\",\n" +
                "        \"comments\":\"评论\"\n" +
                "    }\n" +
                "}",JmsMessage.class);
         RegisterInfoDto o = gson.fromJson(gson.toJson(jsm.getObject()),RegisterInfoDto.class);
    }
}
