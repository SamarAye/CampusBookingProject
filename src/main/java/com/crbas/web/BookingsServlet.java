package com.crbas.web;

import com.crbas.dao.BookingDAO;
import com.crbas.dao.DBConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/bookings")
public class BookingsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Bookings</h1>");

        try (Connection con = DBConnection.getConnection()) {
            // Debug: confirm which DB Tomcat is using
            resp.getWriter().println("<p>DB URL: " + con.getMetaData().getURL() + "</p>");
            resp.getWriter().println("<p>DB User: " + con.getMetaData().getUserName() + "</p>");
            resp.getWriter().println("<p>DB Name: " + con.getCatalog() + "</p>");

            BookingDAO dao = new BookingDAO();
            List<String> rows = dao.getAllBookingsSummary();

            resp.getWriter().println("<p>Rows found: " + rows.size() + "</p>");
            resp.getWriter().println("<ul>");
            for (String b : rows) {
                resp.getWriter().println("<li>" + b + "</li>");
            }
            resp.getWriter().println("</ul>");

        } catch (Exception e) {
            resp.getWriter().println("<pre>ERROR: " + e + "</pre>");
            e.printStackTrace();
        }
    }
}