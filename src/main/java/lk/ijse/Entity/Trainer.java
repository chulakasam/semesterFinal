package lk.ijse.Entity;

public class Trainer {
    private String trainerId;
    private String name;
    private int tel;

    private String nic;
    private String email;
    private String gender;
    private String dob;
    private String desc;

    public Trainer(){

    }
    public Trainer(String trainerId,String name,int tel,String nic,String email,String gender,String dob,String desc){
        this.trainerId=trainerId;
        this.name=name;
        this.tel=tel;
        this.nic=nic;
        this.email=email;
        this.gender=gender;
        this.dob=dob;
        this.desc=desc;
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


    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
