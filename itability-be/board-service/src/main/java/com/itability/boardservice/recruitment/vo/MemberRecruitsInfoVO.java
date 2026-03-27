package com.itability.boardservice.recruitment.vo;

import com.itability.boardservice.recruitment.aggregate.RecruitStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberRecruitsInfoVO {

    private int memberRecruitInfoId;

    private int recruitId;

    private long memberId;

    private RecruitStatus recruitStatus;
}
