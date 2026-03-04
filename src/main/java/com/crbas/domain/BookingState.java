package com.crbas.domain;

public interface BookingState {
    String getName();

    void approve(Booking booking);
    void deny(Booking booking);
    void cancel(Booking booking);
}
