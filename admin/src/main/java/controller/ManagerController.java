package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Manager;
import pojo.MerchantInfo;
import service.AdminManager;

/**
 * @author Carrie
 * @date 2018-8-3 17:43
 */
@RestController
@RequestMapping(value="/manager")
public class ManagerController {

    @Autowired
    private AdminManager adminManager;

    @PostMapping(value="/find")
    public String findManager(@RequestBody Manager manager){
        if(adminManager.findManager(manager)!=null){
            return "yes";
        }
        return "no";
    }
}
