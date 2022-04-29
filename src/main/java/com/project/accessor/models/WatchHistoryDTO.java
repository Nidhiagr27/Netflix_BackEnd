package com.project.accessor.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class WatchHistoryDTO {
    private String profileID;
    private String videoID;
    private double rating;
    private Date lastWatchedAt;
    private Date firstWatchedAt;

}