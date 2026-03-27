package com.itability.boardservice.Selenium.repository;

import com.itability.boardservice.Selenium.entity.CrawlEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CrawlRepository extends JpaRepository<CrawlEntity, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM JobListing")
    void deleteAllEntries();
}
