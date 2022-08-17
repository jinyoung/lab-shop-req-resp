package labshopreqresp.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "delivery", url = "${api.url.delivery}")
public interface DeliveryService {
    @RequestMapping(method = RequestMethod.POST, path = "/deliveries")
    public void startDelivery(@RequestBody Delivery delivery);
    // keep

}
