package com.oscngl.spring.boot.mongodb.service.impl;

import com.oscngl.spring.boot.mongodb.model.MongoSequence;
import com.oscngl.spring.boot.mongodb.service.MongoSequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class MongoSequenceGeneratorServiceImpl implements MongoSequenceGeneratorService {

    private final MongoOperations mongoOperations;

    @Override
    public Long generateSequence(String sequence) {
        MongoSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequence)),
                new Update().inc("sequence",1), options().returnNew(true).upsert(true),
                MongoSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }

}
