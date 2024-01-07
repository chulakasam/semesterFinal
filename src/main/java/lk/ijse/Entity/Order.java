package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Order {
    private String orderId;
    private String date;
    private  String clientId;
    private  double amount;
}
