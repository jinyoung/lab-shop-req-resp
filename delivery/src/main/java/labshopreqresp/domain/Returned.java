package labshopreqresp.domain;

import java.util.*;
import labshopreqresp.domain.*;
import labshopreqresp.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class Returned extends AbstractEvent {

    private Long id;
    private Long orderId;

    public Returned(Shipping aggregate) {
        super(aggregate);
    }

    public Returned() {
        super();
    }
    // keep

}
