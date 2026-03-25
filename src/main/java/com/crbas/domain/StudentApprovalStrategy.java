package com.crbas.domain;

public class StudentApprovalStrategy implements BookingApprovalStrategy {

    @Override
    public boolean approve(Booking booking, boolean resourceAvailable) {
        if (!resourceAvailable) {
            booking.deny();
            return false;
        }
        return false; 
    }
}