package com.crbas.service;

public class BookingSubmissionResult {
    private int generatedId;
    private int userId;
    private int resourceId;
    private String role;
    private boolean resourceAvailable;
    private boolean autoApproved;
    private String finalStatus;

    public BookingSubmissionResult(int generatedId, int userId, int resourceId, String role,
                                   boolean resourceAvailable, boolean autoApproved, String finalStatus) {
        this.generatedId = generatedId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.role = role;
        this.resourceAvailable = resourceAvailable;
        this.autoApproved = autoApproved;
        this.finalStatus = finalStatus;
    }

    public int getGeneratedId() {
        return generatedId;
    }

    public int getUserId() {
        return userId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getRole() {
        return role;
    }

    public boolean isResourceAvailable() {
        return resourceAvailable;
    }

    public boolean isAutoApproved() {
        return autoApproved;
    }

    public String getFinalStatus() {
        return finalStatus;
    }
}