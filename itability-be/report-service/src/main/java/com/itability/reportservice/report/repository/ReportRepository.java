package com.itability.reportservice.report.repository;

import com.itability.reportservice.report.aggregate.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


}
