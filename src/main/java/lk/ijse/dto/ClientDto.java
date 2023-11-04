package lk.ijse.dto;

public class ClientDto {
    private String id;
    private String name;
    private String address;
    private int tel;

    public ClientDto(){}
    public ClientDto(String id, String name, String address, int tel){
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
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


    @Override
    public String toString(){
        return "ClientDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

}
