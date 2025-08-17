package com.helphub.service;

import com.helphub.model.AcceptorRequest;
import com.helphub.model.DonorDonation;
import com.helphub.repository.AcceptorRequestRepository;
import com.helphub.repository.DonorDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonorDonationService {

    @Autowired
    private AcceptorRequestRepository acceptorRequestRepository;

    @Autowired
    private DonorDonationRepository donorDonationRepository;

    @Autowired
    private NotificationService notificationService;

    // ✅ Save a new donation and notify acceptor
    @Transactional
    public void donate(Long requestId, DonorDonation donation) {
        AcceptorRequest request = acceptorRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Acceptor request not found with ID: " + requestId));

        // Mark request as fulfilled
        request.setFulfilled(true);
        acceptorRequestRepository.save(request);

        // Link and save donation
        donation.setSelectedRequest(request);
        donation.setStatus("Pending");

        donorDonationRepository.save(donation);

        // ✅ Notify the acceptor of the new donation
        notificationService.notifyAcceptorOnDonation(request);
    }

    // ✅ Get all donations
    public List<DonorDonation> getAllDonations() {
        return donorDonationRepository.findAll();
    }
    public List<DonorDonation> getDonationsByEmail(String email) {
        return donorDonationRepository.findByDonorEmail(email);
    }


    // ✅ Update a donation and notify
    public void updateDonation(Long id, DonorDonation updated) {
        DonorDonation donation = donorDonationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found with ID: " + id));

        donation.setDonorName(updated.getDonorName());
        donation.setDonorPhoneNum(updated.getDonorPhoneNum());
        donation.setDonorGPSLocation(updated.getDonorGPSLocation());
        donation.setCategories(updated.getCategories());
        donation.setStatus(updated.getStatus());

        donorDonationRepository.save(donation);

        // ✅ Notify the acceptor about the update
        AcceptorRequest acceptor = donation.getSelectedRequest();
        if (acceptor != null) {
            notificationService.notifyAcceptorOnUpdate(acceptor);
        }
    }

    // ✅ Cancel a donation and notify
    public void deleteDonation(Long id) {
        DonorDonation donation = donorDonationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found with ID: " + id));

        AcceptorRequest acceptor = donation.getSelectedRequest();

        donorDonationRepository.deleteById(id);

        // ✅ Notify the acceptor about the cancellation
        if (acceptor != null) {
            notificationService.notifyAcceptorOnCancel(acceptor);
        }
    }
}
