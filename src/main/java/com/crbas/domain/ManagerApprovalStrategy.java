package com.crbas.domain;

public class ManagerApprovalStrategy implements BookingApprovalStrategy {

    @Override
    public boolean approve(Booking booking, boolean resourceAvailable) {
        booking.approve();
        return true;
    }
}