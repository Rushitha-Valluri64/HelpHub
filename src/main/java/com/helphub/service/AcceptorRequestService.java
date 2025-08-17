package com.helphub.service;

import com.helphub.model.AcceptorRequest;
import com.helphub.repository.AcceptorRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcceptorRequestService {

    @Autowired
    private AcceptorRequestRepository acceptorRequestRepository;

    public AcceptorRequest createRequest(AcceptorRequest request) {
        return acceptorRequestRepository.save(request);
    }

    public List<AcceptorRequest> getAllRequests() {
        return acceptorRequestRepository.findByFulfilledFalse();
    }

    public List<AcceptorRequest> getRequestsByCategory(String category) {
        return acceptorRequestRepository.findByCategoriesContainingIgnoreCaseAndFulfilledFalse(category);
    }

    public AcceptorRequest updateRequest(Long id, AcceptorRequest updatedRequest) {
        Optional<AcceptorRequest> optionalRequest = acceptorRequestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            AcceptorRequest existing = optionalRequest.get();
            existing.setName(updatedRequest.getName());
            existing.setPhone(updatedRequest.getPhone());
            existing.setLocation(updatedRequest.getLocation());
            existing.setCategories(updatedRequest.getCategories());
            return acceptorRequestRepository.save(existing);
        } else {
            throw new RuntimeException("Request not found with ID: " + id);
        }
    }
    public List<AcceptorRequest> getRequestsByEmail(String email) {
        return acceptorRequestRepository.findByEmail(email);
    }

    public void deleteRequest(Long id) {
        if (!acceptorRequestRepository.existsById(id)) {
            throw new RuntimeException("❌ Cannot delete. Request not found.");
        }
        acceptorRequestRepository.deleteById(id);
    }
}
