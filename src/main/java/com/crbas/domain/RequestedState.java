package com.crbas.domain;

public class RequestedState extends AbstractBookingState {

    @Override
    public String getName() {
        return "Requested";
    }

    @Override
    public void approve(Booking booking) {
        booking.setState(new ApprovedState());
    }

    @Override
    public void deny(Booking booking) {
        booking.setState(new DeniedState());
    }

    @Override
    public void cancel(Booking booking) {
        booking.setState(new CancelledState());
    }
}
