package com.helphub.repository;

import com.helphub.model.DonorDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DonorDonationRepository extends JpaRepository<DonorDonation, Long> {
	List<DonorDonation> findByDonorEmail(String donorEmail);
	}
