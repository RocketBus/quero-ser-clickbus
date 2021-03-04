package com.clickbus.challenge.request;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "places")
public class Place {
    @Id
    private String id;
    private String name;
    private String slug;
    private String city;
    private String state;
    private Date createdAt;
    private Date updatedAt;

}
