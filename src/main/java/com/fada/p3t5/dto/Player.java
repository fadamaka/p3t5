package com.fada.p3t5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Player {

    @Id
    private String id;
    private String Name;
    private String apiKey;
}
