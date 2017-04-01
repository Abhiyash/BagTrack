package com.example.abhiyash.bagtrack;

/**
 * Created by Abhiyash on 01-Apr-17.
 */

public class FetchBagNo {
    String  baggage_id;
    String  scanner_id;

    public void setBaggage_id(String baggage_id) {
        this.baggage_id = baggage_id;
    }

    public void setScanner_id(String scanner_id) {
        this.scanner_id = scanner_id;
    }

    public String getBaggage_id() {

        return baggage_id;
    }

    public String getScanner_id() {
        return scanner_id;
    }
}
