package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pojo.Advertisement;
import pojo.Complaint;
import pojo.MerchantInfo;
import pojo.UnReadCount;
import service.AdvertisementManager;
import service.AssitService;
import service.ComplaintManager;
import service.MerchantInfoManager;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/4/2018 7:45 PM
 */
@Component
public class AssitServiceImpl implements AssitService {
    @Autowired
    private MerchantInfoManager mManager;
    @Autowired
    private AdvertisementManager adManager;
    @Autowired
    private ComplaintManager cManager;
    int mCount = 0,adCount = 0,cCount = 0;
    @Override
    public UnReadCount getUnReadCount() throws Exception{
        mCount=0;
        adCount=0;
        cCount=0;
        List<MerchantInfo> merchantInfos = mManager.findAllMerchantInfo();
        List<Advertisement> advertisements = adManager.getAllAds();
        List<Complaint> complaints = cManager.getAllComplaints();
        for (MerchantInfo m:merchantInfos){
            if (m.getIsRead().equals("false")){
                mCount++;
            }
        }
        for (Advertisement ad:advertisements){
            if (ad.getIsRead().equals("false")){
                adCount++;
            }
        }
        for (Complaint c:complaints){
            if (c.getIsRead().equals("false")){
                cCount++;
            }
        }
        return new UnReadCount(adCount,mCount,cCount);
    }
}
