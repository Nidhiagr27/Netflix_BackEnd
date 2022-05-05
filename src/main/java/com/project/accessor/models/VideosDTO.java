package com.project.accessor.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class VideosDTO {
    private String videoId;
    private String name;
    private String seriesId;
    private String showId;
    private double rating;
    private Date releaseDate;
    private  Integer totalLength;

}
