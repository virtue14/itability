package com.itability.boardservice.reple.repository;

import com.itability.boardservice.feed.dto.FeedDTO;
import com.itability.boardservice.reple.aggregate.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity,Integer> {
    List<CommentEntity> findByBoardId(FeedDTO boardId);


    void deleteAllByBoardIdBoardId(int boardId);
}
