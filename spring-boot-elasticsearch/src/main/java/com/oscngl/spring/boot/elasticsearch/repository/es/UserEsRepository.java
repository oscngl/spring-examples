package com.oscngl.spring.boot.elasticsearch.repository.es;

import com.oscngl.spring.boot.elasticsearch.model.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEsRepository extends ElasticsearchRepository<User, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"firstName\": \"?0\"}}]}}")
    List<User> findByFirstNameUsingCustomQuery(String firstName);

}
