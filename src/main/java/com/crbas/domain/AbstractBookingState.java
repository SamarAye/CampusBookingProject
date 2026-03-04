package com.crbas.domain;

public abstract class AbstractBookingState implements BookingState {

    @Override
    public void approve(Booking booking) {
        throw new IllegalStateException("Approve not allowed in state: " + getName());
    }

    @Override
    public void deny(Booking booking) {
        throw new IllegalStateException("Deny not allowed in state: " + getName());
    }

    @Override
    public void cancel(Booking booking) {
        throw new IllegalStateException("Cancel not allowed in state: " + getName());
    }
}
