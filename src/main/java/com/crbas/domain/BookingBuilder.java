package com.crbas.domain;

public class BookingBuilder {

    private int bookingId;
    private int userId;
    private int resourceId;
    private String status = "Requested";

    public BookingBuilder bookingId(int bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingBuilder userId(int userId) {
        this.userId = userId;
        return this;
    }

    public BookingBuilder resourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public BookingBuilder status(String status) {
        this.status = status;
        return this;
    }

    public Booking build() {
        if (userId <= 0) throw new IllegalStateException("userId must be set before calling build()");
        if (resourceId <= 0) throw new IllegalStateException("resourceId must be set before calling build()");

        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setUserId(userId);
        booking.setResourceId(resourceId);
        booking.setState(BookingStateFactory.fromStatus(status));
        return booking;
    }
}
