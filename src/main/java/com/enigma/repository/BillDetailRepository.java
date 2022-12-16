package com.enigma.repository;

import com.enigma.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
}
