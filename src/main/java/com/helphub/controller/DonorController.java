package com.helphub.controller;

import com.helphub.dto.DonorDonationDto;
import com.helphub.model.AcceptorRequest;
import com.helphub.model.DonorDonation;
import com.helphub.repository.AcceptorRequestRepository;
import com.helphub.service.DonorDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donation")
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorDonationService donorService;

    @Autowired
    private AcceptorRequestRepository acceptorRequestRepository;

    // ✅ Donate to a request
    @PostMapping("/donate")
    public ResponseEntity<String> donate(@RequestBody DonorDonationDto donationDto) {
        Optional<AcceptorRequest> optionalRequest = acceptorRequestRepository.findById(donationDto.getSelectedRequestId());

        if (optionalRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Acceptor request not found");
        }

        AcceptorRequest request = optionalRequest.get();

        DonorDonation donation = new DonorDonation();
        donation.setDonorName(donationDto.getDonorName());
        donation.setDonorPhoneNum(donationDto.getDonorPhoneNum());
        donation.setDonorGPSLocation(donationDto.getDonorGPSLocation());
        donation.setCategories(donationDto.getCategories());
        donation.setDonorEmail(donationDto.getDonorEmail());
        donation.setStatus("Pending");
        donation.setSelectedRequest(request);

        donorService.donate(request.getId(), donation);

        return ResponseEntity.ok("✅ Donation accepted and request removed.");
    }

    // ✅ View all donations
    @GetMapping("/all")
    public ResponseEntity<List<DonorDonation>> getAllDonations() {
        return ResponseEntity.ok(donorService.getAllDonations());
    }

    // ✅ Update a donation
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable Long id, @RequestBody DonorDonation updatedDonation) {
        try {
            donorService.updateDonation(id, updatedDonation);
            return ResponseEntity.ok("✅ Donation updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ " + e.getMessage());
        }
    }
    @GetMapping("/by-email")
    public ResponseEntity<List<DonorDonation>> getDonationsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(donorService.getDonationsByEmail(email));
    }

    // ✅ Cancel a donation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDonation(@PathVariable Long id) {
        try {
            donorService.deleteDonation(id);
            return ResponseEntity.ok("🗑 Donation canceled successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ " + e.getMessage());
        }
    }
}
