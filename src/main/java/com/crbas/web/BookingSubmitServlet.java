package com.crbas.web;

import com.crbas.service.BookingService;
import com.crbas.service.BookingSubmissionResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/submit")
public class BookingSubmitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Submit Booking</title></head><body>");
        out.println("<h2>Submit a Booking</h2>");
        out.println("<form method='POST' action='submit'>");
        out.println("<label>User ID: <input type='number' name='userId' required /></label><br/><br/>");
        out.println("<label>Resource ID: <input type='number' name='resourceId' required /></label><br/><br/>");
        out.println("<label>Role:");
        out.println("<select name='role'>");
        out.println("<option value='Student'>Student</option>");
        out.println("<option value='Staff'>Staff</option>");
        out.println("<option value='Manager'>Manager</option>");
        out.println("</select>");
        out.println("</label><br/><br/>");
        out.println("<button type='submit'>Submit Booking</button>");
        out.println("</form>");
        out.println("<br/><a href='bookings'>View all bookings</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            int userId = Integer.parseInt(req.getParameter("userId").trim());
            int resourceId = Integer.parseInt(req.getParameter("resourceId").trim());
            String role = req.getParameter("role");

            BookingSubmissionResult result = bookingService.submitBookingWithResult(userId, resourceId, role);

            out.println("<!DOCTYPE html><html><head><title>Booking Result</title></head><body>");
            out.println("<h2>Booking Submitted</h2>");
            if (result.getGeneratedId() > 0) {
                out.println("<p><strong>booking_id:</strong> " + result.getGeneratedId() + "</p>");
                out.println("<p><strong>user_id:</strong> " + result.getUserId() + "</p>");
                out.println("<p><strong>resource_id:</strong> " + result.getResourceId() + "</p>");
                out.println("<p><strong>role:</strong> " + result.getRole() + "</p>");
                out.println("<p><strong>resource available:</strong> " + result.isResourceAvailable() + "</p>");
                out.println("<p><strong>auto-approved:</strong> " + result.isAutoApproved() + "</p>");
                out.println("<p><strong>final status:</strong> <b>" + result.getFinalStatus() + "</b></p>");
            } else {
                out.println("<p style='color:red'>Failed to save booking. Check server logs.</p>");
            }
            out.println("<br/><a href='submit'>Submit another</a> | <a href='bookings'>View all bookings</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<p style='color:red'>Invalid input: userId and resourceId must be integers.</p>");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("<pre>ERROR: " + e + "</pre>");
            e.printStackTrace();
        }
    }
}
