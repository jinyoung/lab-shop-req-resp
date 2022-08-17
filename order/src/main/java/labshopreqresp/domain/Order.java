package labshopreqresp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopreqresp.OrderApplication;
import labshopreqresp.domain.OrderCancelled;
import labshopreqresp.domain.OrderPlaced;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;

    private Integer qty;

    private String customerId;

    private OrderDetail orderDetail;

    @Embedded
    private Payment payment;

    @Embedded
    private Money price;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshopreqresp.external.Shipping shipping = new labshopreqresp.external.Shipping();
        // mappings goes here
        OrderApplication.applicationContext
            .getBean(labshopreqresp.external.ShippingService.class)
            .startDelivery(shipping);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {
        // Get request from Inventory
        //labshopreqresp.external.Inventory inventory =
        //    Application.applicationContext.getBean(labshopreqresp.external.InventoryService.class)
        //    .getInventory(/** mapping value needed */);

    }

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
