package com.crbas.dao;

import com.crbas.domain.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public int saveBooking(Booking booking) {
        String sql = "INSERT INTO Bookings (user_id, resource_id, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getResourceId());
            ps.setString(3, booking.getStatus());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean isResourceAvailable(int resourceId) {
        String sql = "SELECT is_available FROM Resources WHERE resource_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resourceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("is_available");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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