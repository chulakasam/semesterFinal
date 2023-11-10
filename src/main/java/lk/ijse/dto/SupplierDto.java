package lk.ijse.dto;

public class SupplierDto {
    private String supplierId;
    private String name;
    private  String Address;
    private  int contactNo;

    public SupplierDto(){
    }
    public SupplierDto(String supplierId,String name,String Address,int contactNo){
        this.supplierId=supplierId;
        this.name=name;
        this.Address=Address;
        this.contactNo=contactNo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "supplier dto {"+"id"+supplierId+"name"+name+"address"+Address+"telNo"+contactNo+"}";
    }
}
