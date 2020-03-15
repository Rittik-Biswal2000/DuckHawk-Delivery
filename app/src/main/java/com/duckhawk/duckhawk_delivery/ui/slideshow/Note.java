package com.duckhawk.duckhawk_delivery.ui.slideshow;

public class Note {
    private  String  Buyer;
    private String Address;

    public  Note(){}

    public Note(String buyer,String address)
    {
        this.Address = address;
        this.Buyer = buyer;
    }

    public void setBuyer(String buyer) {
        this.Buyer = buyer;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getAddress()
    {
        return Address;
    }

    public String getBuyer()
    {
        return Buyer;
    }
}
