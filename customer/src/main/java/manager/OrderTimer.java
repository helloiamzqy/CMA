package manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.OrderService;

@Component
public class OrderTimer {
    @Autowired
    private OrderService orderService;

    @Scheduled(cron="0/5 * * * * ? ")
    public void autoSuccessOrder(){
        //orderService.findOrderByStatus();
    }
}
