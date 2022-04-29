package com.project.accessor.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class VideosDTO {
    private String videoID;
    private String name;
    private String seriesID;
    private String showID;
    private double rating;
    private Date releaseDate;
    private  Integer totalLength;

}
