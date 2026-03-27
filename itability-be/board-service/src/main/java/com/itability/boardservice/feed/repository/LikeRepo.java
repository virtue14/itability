package com.itability.boardservice.feed.repository;


import com.itability.boardservice.feed.dto.LikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepo extends JpaRepository<LikeDTO,Integer> {
    List<LikeDTO> findByBoardIdBoardId(int boardId);

    void deleteAllByBoardIdBoardId(int boardId);
}
