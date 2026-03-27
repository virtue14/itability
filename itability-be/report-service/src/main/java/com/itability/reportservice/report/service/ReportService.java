package com.itability.reportservice.report.service;

import com.itability.reportservice.client.MemberClient;
import com.itability.reportservice.report.aggregate.Member;
import com.itability.reportservice.report.aggregate.Report;
import com.itability.reportservice.report.dto.ReportDTO;
import com.itability.reportservice.report.repository.ReportRepository;
import com.itability.reportservice.report.repository.ResponseMemberRepository;
import com.itability.reportservice.vo.ResponseMember;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    private final MemberClient memberClient;

    @Autowired
    public ReportService(ReportRepository reportRepository, ModelMapper modelMapper, MemberClient memberClient) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.memberClient = memberClient;
    }

    public Report createOrUpdateReport(ReportDTO reportDTO) {
        Report report = modelMapper.map(reportDTO, Report.class);

        memberClient.reportMember(reportDTO.getReportTargetId());

        return reportRepository.save(report);
    }

    public Optional<Report> findReportById(Integer reportId) {
        return reportRepository.findById(reportId);
    }

   
    public List<ReportDTO> findReportList() { // 메서드 이름 변경
        List<Report> reportList = reportRepository.findAll(Sort.by("reportId"));
        List<ResponseMember> members= memberClient.getAllMember();
        System.out.println("members = " + members);
        return reportList.stream()
                .map(member -> modelMapper.map(member, ReportDTO.class))
                .collect(Collectors.toList());
    }
}
