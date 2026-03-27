//package com.itability.boardservice.search.repository;
//
//import com.itability.boardservice.search.entity.CommentIndex;
//import com.itability.boardservice.search.entity.SearchItem;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface CommentIndexRepository extends ElasticsearchRepository<CommentIndex, Integer> {
//    List<CommentIndex> findByCmtContentContaining(String query);
//}
