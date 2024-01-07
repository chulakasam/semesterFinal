package lk.ijse.Entity;

public class Client {
    private String id;
    private String name;
    private String address;
    private int tel;
    private String email;
    private int height;
    private int weight;
    private String gender;
    private String dob;

    public Client(){}
    public Client(String id, String name, String address, int tel,String email,int height,int weight,String gender,String dob){

        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email=email;
        this.height=height;
        this.weight=weight;
        this.gender=gender;
        this.dob=dob;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getTel() {
        return tel;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(int height) {

        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
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
    @Override
    public String toString(){
        return "ClientDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\''+email +height+weight+gender+dob+
                '}';
    }
}
