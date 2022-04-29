package com.project.accessor.models;

import lombok.Data;

import java.util.Date;


@Data
public class ProfileDTO {
    private String profileID;
    private String name;
    private ProfileType type; //general, kids, adults
    private Date createdAt;
    private String userID;

}
