package Dto;

public class Cart {
    private String name;
    private String servicename;
    private String qty;
    public Cart(String name, String servicename, String qty) {
        this.name = name;
        this.servicename = servicename;
        this.qty = qty;
    }

    public Cart(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String Name) {
        this.name = Name;
    }

    public String getservicename() { return servicename; }
    public void setservicename(String servicename) {
        this.servicename = servicename;
    }

    public String getqty() { return qty; }
    public void setqty(String qty) {
        this.qty = qty;
    }
}
