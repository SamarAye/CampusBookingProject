package com.crbas.dao;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection con = DBConnection.getConnection()) {
            System.out.println("DB connected OK: " + (con != null && !con.isClosed()));

            BookingDAO dao = new BookingDAO();
            for (String b : dao.getAllBookingsSummary()) {
                System.out.println(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}