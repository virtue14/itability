package com.itability.boardservice.MemberClientTestVO;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoDTO {
    private long memberId;

    private String email;

    private String provider;

    private String pwd;

    private String name;

    private String birthDate;

    private String phone;

    private boolean blacklistStatus;

    private int blacklistCount;

    private int mbReportCount;

}