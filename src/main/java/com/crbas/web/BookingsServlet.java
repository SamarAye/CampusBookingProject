package com.crbas.web;

import com.crbas.domain.Booking;
import com.crbas.service.BookingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookings")
public class BookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Bookings</h1>");

        try {
            List<Booking> bookings = bookingService.getAllBookings();

            resp.getWriter().println("<ul>");
            for (Booking booking : bookings) {
                resp.getWriter().println(
                        "<li>booking_id=" + booking.getBookingId()
                                + ", user_id=" + booking.getUserId()
                                + ", resource_id=" + booking.getResourceId()
                                + ", status=" + booking.getStatus()
                                + "</li>"
                );
            }
            resp.getWriter().println("</ul>");

        } catch (Exception e) {
            resp.getWriter().println("<pre>ERROR: " + e + "</pre>");
            e.printStackTrace();
        }
    }
}