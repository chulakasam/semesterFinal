package lk.ijse.dto.Tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientTm {
    private String Id;
    private String name;
    private String address;
    private int tel;
    private String email;
    private int height;
    private int weight;
    private String gender;
    private String dob;
}
