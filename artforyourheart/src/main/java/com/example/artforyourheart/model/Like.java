package com.example.artforyourheart.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@NoArgsConstructor is setter injection
@NoArgsConstructor
@Document(collection="likes")
public class Like {
    @Id
    private String id;
    private String likerId;
    private String likeeId;
}
