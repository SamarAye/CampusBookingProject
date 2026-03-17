package com.crbas.service;

import com.crbas.dao.BookingDAO;
import com.crbas.domain.ApprovalStrategyFactory;
import com.crbas.domain.Booking;
import com.crbas.domain.BookingApprovalStrategy;
import com.crbas.domain.BookingBuilder;

import java.util.List;

public class BookingService {

    private BookingDAO bookingDAO = new BookingDAO();

    public BookingSubmissionResult submitBookingWithResult(int userId, int resourceId, String role) {

        Booking booking = new BookingBuilder()
                .userId(userId)
                .resourceId(resourceId)
                .status("Requested")
                .build();

        boolean resourceAvailable = bookingDAO.isResourceAvailable(resourceId);

        BookingApprovalStrategy strategy = ApprovalStrategyFactory.forRole(role);

        boolean autoApproved = strategy.approve(booking, resourceAvailable);

        int generatedId = bookingDAO.saveBooking(booking);

        return new BookingSubmissionResult(
                generatedId,
                userId,
                resourceId,
                role,
                resourceAvailable,
                autoApproved,
                booking.getStatus()
        );
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }
}