//package com.itability.boardservice.search.repository;
//
//import com.itability.boardservice.search.entity.BoardIndex;
//import com.itability.boardservice.search.entity.SearchItem;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface BoardIndexRepository extends ElasticsearchRepository<BoardIndex, String> {
//    List<BoardIndex> findByBoardContentContaining(String query);
//}
