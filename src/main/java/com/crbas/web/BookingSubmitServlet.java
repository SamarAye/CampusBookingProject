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

    private final BookingService bookingService = new BookingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String contextPath = req.getContextPath();

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Submit Booking</title></head><body>");
            out.println("<h2>Submit a Booking</h2>");
            out.println("<form method='POST' action='" + contextPath + "/submit'>");

            out.println("<label>User ID: <input type='number' name='userId' min='1' required /></label><br/><br/>");
            out.println("<label>Resource ID: <input type='number' name='resourceId' min='1' required /></label><br/><br/>");

            out.println("<label>Role: ");
            out.println("<select name='role' required>");
            out.println("<option value='Student'>Student</option>");
            out.println("<option value='Staff'>Staff</option>");
            out.println("<option value='Manager'>Manager</option>");
            out.println("</select>");
            out.println("</label><br/><br/>");

            out.println("<button type='submit'>Submit Booking</button>");
            out.println("</form>");

            out.println("<br/><a href='" + contextPath + "/bookings'>View all bookings</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String contextPath = req.getContextPath();

        try (PrintWriter out = resp.getWriter()) {
            String userIdParam = req.getParameter("userId");
            String resourceIdParam = req.getParameter("resourceId");
            String role = req.getParameter("role");

            if (userIdParam == null || userIdParam.trim().isEmpty()
                    || resourceIdParam == null || resourceIdParam.trim().isEmpty()
                    || role == null || role.trim().isEmpty()) {

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
                out.println("<p style='color:red'>Missing required input values.</p>");
                out.println("<br/><a href='" + contextPath + "/submit'>Go back</a>");
                out.println("</body></html>");
                return;
            }

            int userId = Integer.parseInt(userIdParam.trim());
            int resourceId = Integer.parseInt(resourceIdParam.trim());

            if (userId <= 0 || resourceId <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
                out.println("<p style='color:red'>User ID and Resource ID must be positive integers.</p>");
                out.println("<br/><a href='" + contextPath + "/submit'>Go back</a>");
                out.println("</body></html>");
                return;
            }

            if (!role.equals("Student") && !role.equals("Staff") && !role.equals("Manager")) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
                out.println("<p style='color:red'>Invalid role selected.</p>");
                out.println("<br/><a href='" + contextPath + "/submit'>Go back</a>");
                out.println("</body></html>");
                return;
            }

            BookingSubmissionResult result =
                    bookingService.submitBookingWithResult(userId, resourceId, role);

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Booking Result</title></head><body>");
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
                out.println("<p style='color:red'>Failed to save booking. Please verify that the user ID and resource ID exist in the database.</p>");
            }

            out.println("<br/><a href='" + contextPath + "/submit'>Submit another</a> | "
                    + "<a href='" + contextPath + "/bookings'>View all bookings</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
                out.println("<p style='color:red'>Invalid input: userId and resourceId must be integers.</p>");
                out.println("<br/><a href='" + req.getContextPath() + "/submit'>Go back</a>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html><html><head><title>Error</title></head><body>");
                out.println("<p style='color:red'>An unexpected error occurred while processing the booking.</p>");
                out.println("<pre>" + e.getMessage() + "</pre>");
                out.println("<br/><a href='" + req.getContextPath() + "/submit'>Go back</a>");
                out.println("</body></html>");
            }
        }
    }
}