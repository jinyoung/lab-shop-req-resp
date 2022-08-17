package labshopreqresp.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Entity
@Data
public class OrderDetail {

    private Long orderItemId;

    private Long productId;

    private Long qty;
}
