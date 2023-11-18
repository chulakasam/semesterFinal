package lk.ijse.dto.Tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemTm {
    private String itemCode;
    private String name;
    private double unitPrice;
    private int QtyOnHand;

}
