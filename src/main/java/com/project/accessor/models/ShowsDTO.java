package com.project.accessor.models;

import lombok.Data;

@Data
public class ShowsDTO {
    private String showID;
    private String name;
    private ShowType type; //movie, tv show
    private Genre genre; //horror, comedy, action, drama
    private ProfileType audience; // General,kids,adults
    private double rating;
    private Integer length;


}
