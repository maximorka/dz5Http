package storage.order.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private StatusOrder status;
    private boolean complete;
}
