package com.duckhawk.duckhawk_delivery.ui.slideshow;

import com.google.firebase.firestore.GeoPoint;

public class Slide {
    private String Buyer;
    private String Address;

    public Slide() {}
    public Slide(String buyer, String address) {
        Buyer = buyer;
        Address = address;
    }

    public String getBuyer() {
        return Buyer;
    }

    public String getAddress() {
        return Address;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

