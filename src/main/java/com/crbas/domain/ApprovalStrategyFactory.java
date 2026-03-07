package com.crbas.domain;

public class ApprovalStrategyFactory {

    public static BookingApprovalStrategy forRole(String role) {
        if (role == null) return new StudentApprovalStrategy();

        switch (role.trim()) {
            case "Staff": return new StaffApprovalStrategy();
            case "Manager": return new ManagerApprovalStrategy();
            case "Student":
            default: return new StudentApprovalStrategy();
        }
    }
}
