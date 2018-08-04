package pojo;

/**
 * @author JohnGao
 * @date 8/4/2018 7:40 PM
 */
public class UnReadCount {
    private int advertisementNewCount;
    private int merchantInfoNewCount;
    private int complaintNewCount;

    public UnReadCount() {
    }

    public UnReadCount(int advertisementNewCount, int merchantInfoNewCount, int complaintNewCount) {
        this.advertisementNewCount = advertisementNewCount;
        this.merchantInfoNewCount = merchantInfoNewCount;
        this.complaintNewCount = complaintNewCount;
    }

    public int getAdvertisementNewCount() {
        return advertisementNewCount;
    }

    public void setAdvertisementNewCount(int advertisementNewCount) {
        this.advertisementNewCount = advertisementNewCount;
    }

    public int getMerchantInfoNewCount() {
        return merchantInfoNewCount;
    }

    public void setMerchantInfoNewCount(int merchantInfoNewCount) {
        this.merchantInfoNewCount = merchantInfoNewCount;
    }

    public int getComplaintNewCount() {
        return complaintNewCount;
    }

    public void setComplaintNewCount(int complaintNewCount) {
        this.complaintNewCount = complaintNewCount;
    }
}
