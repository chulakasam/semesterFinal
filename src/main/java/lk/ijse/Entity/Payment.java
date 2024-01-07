package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    private  String paymentId;
    private  String date;
    private double amount;
    private String clientId;
    private String orderId;
    private String type;

}
