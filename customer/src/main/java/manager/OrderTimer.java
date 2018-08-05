package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.OrderService;

@Component
public class OrderTimer {
    @Autowired
    private OrderService orderService;
    @Value("${AUTO_SUCCESS_TIME}")
    private String AUTO_SUCCESS_TIME;

    @Scheduled(cron="0 */10 * * * ?")
    public void autoSuccessOrder(){
        orderService.autoSuccessOrder(AUTO_SUCCESS_TIME);
    }
}
