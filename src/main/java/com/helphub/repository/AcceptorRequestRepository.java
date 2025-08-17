package com.helphub.repository;

import com.helphub.model.AcceptorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcceptorRequestRepository extends JpaRepository<AcceptorRequest, Long> {
    List<AcceptorRequest> findByFulfilledFalse();
    List<AcceptorRequest> findByCategoriesContainingIgnoreCaseAndFulfilledFalse(String category);
    List<AcceptorRequest> findByEmail(String email);
}
