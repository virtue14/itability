package com.itability.reportservice.blackllist.repository;

import com.itability.reportservice.blackllist.aggregate.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {
}
