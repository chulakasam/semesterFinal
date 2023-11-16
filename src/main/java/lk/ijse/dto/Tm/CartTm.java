package lk.ijse.dto.Tm;


import javafx.scene.control.Button;

public class CartTm {

    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double tot;
    private Button btn;

    public CartTm() {

    }
    public CartTm(String code, String description, int qty, double unitPrice, double tot, Button btn) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.tot = tot;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }


    @Override
    public String toString() {
        return "{"+code+description+qty+unitPrice+tot+btn+"}";
    }
}
