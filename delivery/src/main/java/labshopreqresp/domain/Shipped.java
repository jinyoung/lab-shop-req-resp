package labshopreqresp.domain;

import java.util.*;
import labshopreqresp.domain.*;
import labshopreqresp.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class Shipped extends AbstractEvent {

    private Long id;
    private Long orderId;

    public Shipped(Shipping aggregate) {
        super(aggregate);
    }

    public Shipped() {
        super();
    }
    // keep

}
