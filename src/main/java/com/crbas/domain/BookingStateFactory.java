package com.crbas.domain;

public class BookingStateFactory {

    public static BookingState fromStatus(String status) {
        if (status == null) return new RequestedState();

        switch (status.trim()) {
            case "Approved":
                return new ApprovedState();
            case "Denied":
                return new DeniedState();
            case "Cancelled":
                return new CancelledState();
            case "Requested":
            default:
                return new RequestedState();
        }
    }
}
