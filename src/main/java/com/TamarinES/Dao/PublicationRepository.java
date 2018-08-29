package com.TamarinES.Dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.TamarinES.Entity.Publication;


@Repository
public interface PublicationRepository extends ElasticsearchRepository<Publication, Integer>{

}
