package com.example.abhiyash.bagtrack;

/**
 * Created by Abhiyash on 01-Apr-17.
 */

public class CheckerBagId{
    String baggage_id;
    String ticket_id;

    public void setBaggage_id(String baggage_id) {
        this.baggage_id = baggage_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getBaggage_id() {
        return baggage_id;
    }

    public String getTicket_id() {
        return ticket_id;
    }
}
