package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class paymentDto {
    private  String paymentId;
    private  String date;
    private double amount;
    private String clientId;
    private String orderId;
    private String type;

}
