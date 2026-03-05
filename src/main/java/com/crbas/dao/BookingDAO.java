package com.crbas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public List<String> getAllBookingsSummary() {
        List<String> results = new ArrayList<>();
        String sql = "SELECT booking_id, user_id, resource_id, status FROM Bookings";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(
                    "booking_id=" + rs.getInt("booking_id") +
                    ", user_id=" + rs.getInt("user_id") +
                    ", resource_id=" + rs.getInt("resource_id") +
                    ", status=" + rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}