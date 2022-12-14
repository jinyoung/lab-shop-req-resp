package labshopreqresp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopreqresp.OrderApplication;
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

    private Double amount;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshopreqresp.external.DecreaseStockCommand decreaseStockCommand = new labshopreqresp.external.DecreaseStockCommand();
        decreaseStockCommand.setQty(getQty());
        // mappings goes here
        OrderApplication.applicationContext
            .getBean(labshopreqresp.external.InventoryService.class)
            .decreaseStock(Long.valueOf(getProductId()), decreaseStockCommand);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {
        // Get request from Inventory
        labshopreqresp.external.Inventory inventory =
           OrderApplication.applicationContext.getBean(labshopreqresp.external.InventoryService.class)
           .getInventory(Long.valueOf(getProductId()));

        if(inventory.getStock() <= 0)
            throw new RuntimeException("Out of stock");

    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
