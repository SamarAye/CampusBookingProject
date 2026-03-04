package com.crbas.domain;

public class ApprovedState extends AbstractBookingState {

    @Override
    public String getName() {
        return "Approved";
    }

    @Override
    public void cancel(Booking booking) {
        booking.setState(new CancelledState());
    }
}
