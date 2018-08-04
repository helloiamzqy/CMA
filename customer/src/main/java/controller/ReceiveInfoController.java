package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Customer;
import pojo.OrderItem;
import pojo.ReceiveInfo;
import service.OrderItemManager;
import service.ReceiveInfoService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/receiveInfo")
public class ReceiveInfoController {
    @Autowired
    private ReceiveInfoService receiveInfoService;

    @ResponseBody
    @PostMapping(value = "/{customerId}")
    public ReceiveInfo addReceiveInfo(@PathVariable(value = "customerId") String customerId, @RequestBody ReceiveInfo receiveInfo) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return receiveInfoService.addReceiveInfo(receiveInfo, customer);
    }

    @ResponseBody
    @GetMapping(value = "/{customerId}")
    public List<ReceiveInfo> getReceiveInfo(@PathVariable(value = "customerId") String customerId) {
        System.out.println("findReceiveInfo");
        Customer customer = new Customer();
        customer.setId(customerId);
        return receiveInfoService.findReceiveInfo(customer);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{receiveId}")
    public ReceiveInfo deleteReceiveInfo(@PathVariable(value = "receiveId") String receiveId) {
        System.out.println("deleteReceiveInfo");
        return receiveInfoService.deleteReceiveInfo(receiveId);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT ,value = "/{customerId}")
    public ReceiveInfo updateReceiveInfo(@PathVariable(value = "customerId") String customerId,@RequestBody ReceiveInfo receiveInfo) {
        Customer customer = new Customer();
        customer.setId(customerId);
        receiveInfo.setCustomer(customer);
        return receiveInfoService.updateReceiveInfo(receiveInfo);
    }
}
