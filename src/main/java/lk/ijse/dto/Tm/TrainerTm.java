package lk.ijse.dto.Tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainerTm {
    private String trainerId;
    private String name;
    private String email;
    private String dob;
    private int tel;
    private String nic;
    private String gender;
    private String desc;
}
