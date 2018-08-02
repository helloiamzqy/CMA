package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Merchant;
import service.MerchantManager;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    private MerchantManager merchantManager;
    @GetMapping
    public List<Merchant> findMerchant(){
        return merchantManager.findMerchant();
    }
    @GetMapping(value = "{name}")
    public Merchant findMerchantByName(@PathVariable(name = "name")String name){
        return merchantManager.findMerchantByName(name);
    }
    @PostMapping
    public Merchant addMerchant(@RequestBody Merchant merchant){
        return merchantManager.addMerchant(merchant);
    }
    @PutMapping
    public Merchant updateMerchant(@RequestBody Merchant merchant){
        return merchantManager.updateMerchant(merchant);
    }


}
