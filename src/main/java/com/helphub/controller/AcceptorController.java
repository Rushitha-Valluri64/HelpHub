package com.helphub.controller;

import com.helphub.model.AcceptorRequest;
import com.helphub.service.AcceptorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // ✅ Allows all frontend origins (adjust as needed)
@RestController
@RequestMapping("/api/acceptor")
public class AcceptorController {

    @Autowired
    private AcceptorRequestService acceptorRequestService;

    @PostMapping("/create")
    public ResponseEntity<AcceptorRequest> createRequest(@RequestBody AcceptorRequest request) {
        AcceptorRequest savedRequest = acceptorRequestService.createRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AcceptorRequest>> getAllRequests() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-store, no-cache, must-revalidate"); // ✅ Prevent caching
        headers.add("Pragma", "no-cache");

        List<AcceptorRequest> requests = acceptorRequestService.getAllRequests();
        return new ResponseEntity<>(requests, headers, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<AcceptorRequest>> getRequestsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(acceptorRequestService.getRequestsByCategory(category));
    }
    @GetMapping("/by-email")
    public ResponseEntity<List<AcceptorRequest>> getRequestsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(acceptorRequestService.getRequestsByEmail(email));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRequest(@PathVariable Long id, @RequestBody AcceptorRequest updatedRequest) {
        try {
            acceptorRequestService.updateRequest(id, updatedRequest);
            return ResponseEntity.ok("✅ Updated successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("❌ " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        try {
            acceptorRequestService.deleteRequest(id);
            return ResponseEntity.ok("✅ Request deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("❌ " + e.getMessage());
        }
    }
}
