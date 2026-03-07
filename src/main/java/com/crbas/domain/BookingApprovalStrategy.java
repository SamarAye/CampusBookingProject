package com.crbas.domain;

public interface BookingApprovalStrategy {

    boolean approve(Booking booking, boolean resourceAvailable);
}
