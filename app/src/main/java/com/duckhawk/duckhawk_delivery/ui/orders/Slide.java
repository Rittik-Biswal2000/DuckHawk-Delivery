package com.duckhawk.duckhawk_delivery.ui.orders;


public class Slide {
    private String buyer;
    private String Address;
    private String prodcat;
    private String productid;
    private String location;
    private String price;
    private String phone;

    public Slide() {}
    public Slide(String Buyer, String address, String prodcat,String productid,String Location, String Phone,String Price) {
        this.buyer = Buyer;
        this.Address = address;
        this.prodcat = prodcat;
        this.productid = productid;
        this.location = Location;
        this.price = Price;
        this.phone= Phone;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getbuyer() {
        return buyer;
    }

    public String getAddress() {
        return Address;
    }

    public void setbuyer(String Buyer) {
        buyer = Buyer;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProdcat() {
        return prodcat;
    }

    public String getProductid() {
        return productid;
    }

    public void setProdcat(String prodcat) {
        this.prodcat = prodcat;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}

