package lk.ijse.Entity;

public class Item {
    private String itemCode;
    private String name;
    private double unitPrice;
    private int qty;
    public Item(){}
    public Item(String itemCode,String name,double unitPrice,int qty){
        this.itemCode=itemCode;
        this.name=name;
        this.unitPrice=unitPrice;
        this.qty=qty;
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "itemDto {"+"itemcode"+itemCode+"name"+name+"price"+unitPrice+"qty"+qty+"}";
    }
}
