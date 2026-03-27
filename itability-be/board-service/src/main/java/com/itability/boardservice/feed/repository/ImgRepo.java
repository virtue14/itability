package com.itability.boardservice.feed.repository;


import com.itability.boardservice.feed.dto.ImgDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgRepo extends JpaRepository<ImgDTO,String> {
}
