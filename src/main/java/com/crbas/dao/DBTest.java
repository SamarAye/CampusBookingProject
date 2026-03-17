package com.crbas.dao;

import com.crbas.domain.Booking;

import java.util.List;

public class DBTest {

    public static void main(String[] args) {
        BookingDAO dao = new BookingDAO();

        List<Booking> bookings = dao.getAllBookings();

        for (Booking booking : bookings) {
            System.out.println(
                    "booking_id=" + booking.getBookingId()
                            + ", user_id=" + booking.getUserId()
                            + ", resource_id=" + booking.getResourceId()
                            + ", status=" + booking.getStatus()
            );
        }
    }
}