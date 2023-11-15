package lk.ijse.dto;

public class TrainerDto {

    private String trainerId;
    private String name;
    private int tel;

    public TrainerDto(){

    }
    public TrainerDto(String trainerId,String name,int tel){
        this.trainerId=trainerId;
        this.name=name;
        this.tel=tel;
    }


    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
