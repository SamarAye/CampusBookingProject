/*
 * Booking lifecycle managed using the State Pattern.
 * Each state controls allowed transitions (Requested -> Approved/Denied/Cancelled).
 * Status string remains synchronized with the database column.
 */
package com.crbas.domain;

public class Booking {
    private int bookingId;
    private int userId;
    private int resourceId;

    // Matches DB: Bookings.status VARCHAR(20)
    private String status;

    // State Pattern
    private BookingState state;

    public Booking() {
        setState(new RequestedState()); // default lifecycle start
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

    // Optional helper (nice for DAO)
    public String getStatusName() {
        return status;
    }

    public void setState(BookingState state) {
        this.state = state;
        this.status = state.getName(); // keep DB status in sync
    }

    // Actions delegate to current state
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
