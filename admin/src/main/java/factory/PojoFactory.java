package factory;

import pojo.Advertisement;
import pojo.Complaint;
import pojo.MerchantInfo;

import java.util.Date;

/**
 * @author JohnGao
 * @date 8/6/2018 9:50 AM
 */
public class PojoFactory {
    public static Advertisement getAdvertisementInstance(){
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("2222");
        advertisement.setPicture("http://10.222.29.191:9091/picture/download/342876fa-a3aa-40cc-b0fc-809ec567b5b3.jpg");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("kfc");
        advertisement.setStatus("1");
        return advertisement;
    }

    public static MerchantInfo getMerchantInfoInstance(){
        return new MerchantInfo("143","123","123","123",
                "http://10.222.29.191:9091/picture/download/342876fa-a3aa-40cc-b0fc-809ec567b5b3.jpg",
                "123","123","123","0","123");
    }

    public static Complaint getComplaintInstance(){
        Complaint comp = new Complaint();
        comp.setMerchantId("sdfs123sdf");
        comp.setMerchantName("ttt");
        comp.setOrderId("10001ad225");
        comp.setCreateTime(new Date());
        comp.setReason("很难啊吃");
        return comp;
    }
}
