package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Merchant;
import service.MerchantManager;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    private MerchantManager merchantManager;
    @GetMapping
    public List<Merchant> findMerchant(){
        return merchantManager.findMerchant();
    }
//    @GetMapping
//    public Merchant merchantLogin(@RequestBody Merchant merchant){
//        return merchantManager.merchantLogin(merchant);
//    }
    @PostMapping(value = "regist")
    public Merchant addMerchant(@RequestBody Merchant merchant){
        return merchantManager.addMerchant(merchant);
    }
    @PutMapping
    public Merchant updateMerchant(@RequestBody Merchant merchant){
        return merchantManager.updateMerchant(merchant);
    }


}
