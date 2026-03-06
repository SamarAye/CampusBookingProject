package com.crbas.domain;

public class Booking {
    private int bookingId;
    private int userId;
    private int resourceId;
    private String status;
    private BookingState state;

    public Booking() {
        setState(new RequestedState());
    }

    public Booking(int userId, int resourceId) {
        this.userId = userId;
        this.resourceId = resourceId;
        setState(new RequestedState());
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public BookingState getState() {
        return state;
    }

    public String getStatus() {
        return status;
    }

    public void setState(BookingState state) {
        this.state = state;
        this.status = state.getName();
    }

    public void approve() {
        state.approve(this);
    }

    public void deny() {
        state.deny(this);
    }

    public void cancel() {
        state.cancel(this);
    }
}
