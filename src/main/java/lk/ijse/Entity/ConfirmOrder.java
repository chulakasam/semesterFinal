package lk.ijse.Entity;

import lk.ijse.dto.Tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmOrder {
    private String orderId;
    private String date;
    private String clientId;
    private double netTotal;
    private List<CartTm> cartTmList = new ArrayList<>();
}
