package com.example.abhiyash.bagtrack;

/**
 * Created by Abhiyash on 01-Apr-17.
 */

public class CheckerBagId{
    String bag_id;
    String ticket_id;

    public void setBaggage_id(String bag_id) {
        this.bag_id = bag_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getBaggage_id() {
        return bag_id;
    }

    public String getTicket_id() {
        return ticket_id;
    }
}
