package controller;

import com.google.gson.Gson;
import dto.RegisterInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Merchant;
import pojo.Pager;
import pojo.RegisterInfo;
import service.RegistInfoManger;

/**
 * @author Dunn
 */
@RestController
public class RegistInfoController {
    @Autowired
    private RegistInfoManger registInfoManger;

    @PostMapping(value = "registinfo/{mid}")
    public RegisterInfo addRegisterInfo(@PathVariable String mid, @RequestBody RegisterInfo registerInfo){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        registerInfo.setMerchant(merchant);
        return registInfoManger.addRegisterInfo(registerInfo);
    }

    @GetMapping(value = "registinfo/{mid}")
    public RegisterInfo getRegisterInfo(@PathVariable String mid){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        return registInfoManger.getRegisterInfo(merchant);
    }
}
