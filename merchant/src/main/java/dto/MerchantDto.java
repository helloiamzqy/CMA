package dto;

import pojo.Merchant;

public class MerchantDto {
    private String nameError;
    private String pswError;
    private Merchant merchant;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPswError() {
        return pswError;
    }

    public void setPswError(String pswError) {
        this.pswError = pswError;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
