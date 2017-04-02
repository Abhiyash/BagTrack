package com.example.abhiyash.bagtrack;

/**
 * Created by Abhiyash on 01-Apr-17.
 */

public class FetchBagNo {
    String  bag_id;
    String  scanner_id;

    public void setBaggage_id(String bag_id) {
        this.bag_id = bag_id;
    }

    public void setScanner_id(String scanner_id) {
        this.scanner_id = scanner_id;
    }

    public String getBaggage_id() {

        return bag_id;
    }

    public String getScanner_id() {
        return scanner_id;
    }
}
