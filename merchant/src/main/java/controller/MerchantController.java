package controller;

import dto.MerchantDto;
import jersey.Config;
import jersey.ServerInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Merchant;
import service.MerchantManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    private MerchantManager merchantManager;
    @Autowired
    private ServerInteraction interaction;
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
    @PostMapping(value = "login")
    public MerchantDto merchantLogin(@RequestBody Merchant merchant, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MerchantDto merchantDto=merchantManager.merchantLogin(merchant);

        if(merchantDto.getPswError()==null&&merchantDto.getNameError()==null&&merchantDto.getMerchant()!=null){
               String status =  interaction.interact(Config.ask_url+merchantDto.getMerchant().getId(),"");
               merchantDto.setStatus(status);
        }

        return merchantDto;
    }

}
