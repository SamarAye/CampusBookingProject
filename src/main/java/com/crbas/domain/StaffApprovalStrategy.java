package com.crbas.domain;

public class StaffApprovalStrategy implements BookingApprovalStrategy {

    @Override
    public boolean approve(Booking booking, boolean resourceAvailable) {
        if (!resourceAvailable) {
            booking.deny();
            return false;
        }
        booking.approve();
        return true;
    }
}