package labshopreqresp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopreqresp.DeliveryApplication;
import labshopreqresp.domain.Returned;
import labshopreqresp.domain.Shipped;
import lombok.Data;

@Entity
@Table(name = "Shipping_table")
@Data
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String productId;

    @PostPersist
    public void onPostPersist() {
        Shipped shipped = new Shipped(this);
        shipped.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        Returned returned = new Returned(this);
        returned.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static ShippingRepository repository() {
        ShippingRepository shippingRepository = DeliveryApplication.applicationContext.getBean(
            ShippingRepository.class
        );
        return shippingRepository;
    }
}
