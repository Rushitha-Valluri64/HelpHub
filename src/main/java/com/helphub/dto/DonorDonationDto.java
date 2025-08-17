package com.helphub.dto;

import java.util.List;

public class DonorDonationDto {

    private String donorName;
    private String donorPhoneNum;
    private String donorGPSLocation;
    private List<String> categories;

    private String status;
    private Long selectedRequestId;
    private String donorEmail;

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    // ✅ Getters and Setters
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhoneNum() {
        return donorPhoneNum;
    }

    public void setDonorPhoneNum(String donorPhoneNum) {
        this.donorPhoneNum = donorPhoneNum;
    }

    public String getDonorGPSLocation() {
        return donorGPSLocation;
    }

    public void setDonorGPSLocation(String donorGPSLocation) {
        this.donorGPSLocation = donorGPSLocation;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSelectedRequestId() {
        return selectedRequestId;
    }

    public void setSelectedRequestId(Long selectedRequestId) {
        this.selectedRequestId = selectedRequestId;
    }
}
