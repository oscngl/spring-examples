package com.oscngl.spring.boot.cassandra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String email;

}
