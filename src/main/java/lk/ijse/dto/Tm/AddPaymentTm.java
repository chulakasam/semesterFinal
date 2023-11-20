package lk.ijse.dto.Tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddPaymentTm {
    private String paymentId;
    private String date;
    private double amount;
    private String clientId;
    private String orderId;
    private String type;

}
