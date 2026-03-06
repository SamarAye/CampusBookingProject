package com.crbas.domain;

public class StudentApprovalStrategy implements BookingApprovalStrategy {

    @Override
    public boolean approve(Booking booking, boolean resourceAvailable) {
        return false;
    }
}
