package com.itability.memberservice.mypage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DegreeDTO {
    private int degreeId;
    private String finalEduName;
    private String enrollDate;
    private String graduateDate;
    private String major;
    private boolean registerStatus;
}
