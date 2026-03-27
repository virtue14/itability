package com.itability.boardservice.recruitment.vo;

import com.itability.boardservice.recruitment.aggregate.RecruitDTO;
import com.itability.boardservice.recruitment.aggregate.RecruitStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMemberApplyVO {

    private int memberRecruitInfoId;

    private RecruitDTO recruitId;

    private long memberId;

    private RecruitStatus recruitStatus;
}

