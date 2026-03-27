package com.itability.memberservice.snsapi.common;

import com.itability.memberservice.img.dao.ImageDAO;
import com.itability.memberservice.img.entity.ImageEntity;
import com.itability.memberservice.img.enumData.IMG_USE;
import com.itability.memberservice.member.dao.MemberInfoRepo;
import com.itability.memberservice.member.dto.MemberInfoDTO;
import com.itability.memberservice.member.dto.Provider;
import com.itability.memberservice.mypage.dao.DegreeDAO;
import com.itability.memberservice.mypage.dao.MemberProfileDAO;
import com.itability.memberservice.mypage.entity.DegreeEntity;
import com.itability.memberservice.mypage.entity.MemberProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    private final MemberInfoRepo memberInfoRepo;
    private final MemberProfileDAO memberProfileDAO;
    private final DegreeDAO degreeDAO;
    private final ImageDAO imageDAO;

    @Autowired
    public CommonService(MemberInfoRepo memberInfoRepo, MemberProfileDAO memberProfileDAO, DegreeDAO degreeDAO, ImageDAO imageDAO) {
        this.memberInfoRepo = memberInfoRepo;
        this.memberProfileDAO = memberProfileDAO;
        this.degreeDAO = degreeDAO;
        this.imageDAO = imageDAO;
    }

    public int existMember(long userId){
        if(memberInfoRepo.existsById(userId)){
            return 1;
        }   return 0;
    }

    public void addUserLogin(Long userId,String imgId ,String name, String email, Provider provider){
        MemberInfoDTO member = new MemberInfoDTO(userId,name,email,provider);
        ImageEntity imageEntity = new ImageEntity(userId, imgId, IMG_USE.profile, "link");
        imageDAO.save(imageEntity);
        MemberProfileEntity profile = new MemberProfileEntity(member,member.getName(), imageEntity);
        DegreeEntity degree = new DegreeEntity();
        degreeDAO.save(degree);
        profile.setDegree(degree);
        memberProfileDAO.save(profile);
    }

    public void addUserLogin2(MemberInfoDTO userEntity) {
        // userId, imgId, IMG_USE.profile, "link"
        ImageEntity imageEntity = new ImageEntity(userEntity.getMemberId(),userEntity.getPicture(),IMG_USE.profile,"link");
        imageDAO.save(imageEntity);
        MemberProfileEntity profile = new MemberProfileEntity(userEntity, userEntity.getName(), imageEntity);
        DegreeEntity degree = new DegreeEntity();
        degreeDAO.save(degree);
        profile.setDegree(degree);
        memberProfileDAO.save(profile);
    }
}
