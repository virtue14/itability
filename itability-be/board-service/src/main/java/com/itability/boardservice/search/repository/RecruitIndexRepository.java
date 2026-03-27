//package com.itability.boardservice.search.repository;
//
//import com.itability.boardservice.search.entity.RecruitIndex;
//import com.itability.boardservice.search.entity.SearchItem;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface RecruitIndexRepository extends ElasticsearchRepository<RecruitIndex, Integer> {
//    List<RecruitIndex> findByRecruitTitleContaining(String query);
//}
