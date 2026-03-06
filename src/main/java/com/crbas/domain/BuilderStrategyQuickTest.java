package com.crbas.domain;

public class BuilderStrategyQuickTest {

    public static void main(String[] args) {

        Booking b1 = new BookingBuilder()
                .userId(1)
                .resourceId(5)
                .status("Requested")
                .build();
        System.out.println("Built booking: userId=" + b1.getUserId()
                + ", resourceId=" + b1.getResourceId()
                + ", status=" + b1.getStatus());

        Booking b2 = new BookingBuilder()
                .bookingId(42)
                .userId(2)
                .resourceId(3)
                .status("Approved")
                .build();
        System.out.println("Built booking: userId=" + b2.getUserId()
                + ", resourceId=" + b2.getResourceId()
                + ", status=" + b2.getStatus());

        String[] roles = { "Student", "Staff", "Manager" };

        boolean[] availabilities = { true, false };

        for (boolean available : availabilities) {
            System.out.println("  [resource available = " + available + "]");
            for (String role : roles) {
                Booking booking = new BookingBuilder()
                        .userId(10)
                        .resourceId(1)
                        .build();

                BookingApprovalStrategy strategy = ApprovalStrategyFactory.forRole(role);
                boolean autoApproved = strategy.approve(booking, available);

                System.out.println("  [" + role + "]  auto-approved=" + autoApproved + "  status=" + booking.getStatus());
            }
        }

        for (String role : roles) {
            Booking booking = new BookingBuilder()
                    .userId(99)
                    .resourceId(2)
                    .status("Requested")
                    .build();

            ApprovalStrategyFactory.forRole(role).approve(booking, true);

            System.out.println("Role=" + role + " => status after strategy: " + booking.getStatus());
        }
    }
}
