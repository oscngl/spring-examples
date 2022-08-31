package com.oscngl.spring.boot.mongodb.service;

public interface MongoSequenceGeneratorService {

    Long generateSequence(String sequence);

}
