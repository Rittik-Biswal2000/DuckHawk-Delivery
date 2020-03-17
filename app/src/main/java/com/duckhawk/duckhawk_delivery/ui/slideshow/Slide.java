package com.duckhawk.duckhawk_delivery.ui.slideshow;


public class Slide {
    private String buyer;
    private String Address;
    private String prodcat;
    private String productid;

    public Slide() {}
    public Slide(String Buyer, String address, String prodcat,String productid) {
        this.buyer = Buyer;
        this.Address = address;
        this.prodcat = prodcat;
        this.productid = productid;
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

