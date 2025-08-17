package com.helphub.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "donor_donations")
public class DonorDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;
    private String donorPhoneNum;
    private String donorGPSLocation;
    private String status;

    @ElementCollection
    private List<String> categories;
 // ✅ Add this field
    @Column(nullable = false)
    private String donorEmail;

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acceptor_request_id", nullable = false)
    private AcceptorRequest selectedRequest;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getDonorPhoneNum() { return donorPhoneNum; }
    public void setDonorPhoneNum(String donorPhoneNum) { this.donorPhoneNum = donorPhoneNum; }

    public String getDonorGPSLocation() { return donorGPSLocation; }
    public void setDonorGPSLocation(String donorGPSLocation) { this.donorGPSLocation = donorGPSLocation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public AcceptorRequest getSelectedRequest() { return selectedRequest; }
    public void setSelectedRequest(AcceptorRequest selectedRequest) { this.selectedRequest = selectedRequest; }
}
