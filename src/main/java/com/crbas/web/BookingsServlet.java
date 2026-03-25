package com.crbas.web;

import com.crbas.domain.Booking;
import com.crbas.service.BookingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/bookings")
public class BookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String contextPath = req.getContextPath();

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Bookings</title></head><body>");
            out.println("<h1>Bookings</h1>");

            List<Booking> bookings = bookingService.getAllBookings();

            if (bookings == null || bookings.isEmpty()) {
                out.println("<p>No bookings found.</p>");
            } else {
                out.println("<ul>");
                for (Booking booking : bookings) {
                    out.println(
                            "<li>booking_id=" + booking.getBookingId()
                                    + ", user_id=" + booking.getUserId()
                                    + ", resource_id=" + booking.getResourceId()
                                    + ", status=" + booking.getStatus()
                                    + "</li>"
                    );
                }
                out.println("</ul>");
            }

            out.println("<br><a href='" + contextPath + "/submit'>Submit a new booking</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("<pre>Error loading bookings: " + e.getMessage() + "</pre>");
        }
    }
}