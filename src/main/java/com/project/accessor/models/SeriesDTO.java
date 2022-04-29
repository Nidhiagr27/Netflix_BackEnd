package com.project.accessor.models;


import lombok.Data;

@Data
public class SeriesDTO {
    private String seriesID;
    private String name;
    private String showID;
    private Integer noOfVideos;
    private double rating;
    private Integer totalLength;

    public SeriesDTO(String seriesID, String name, String showID, Integer noOfVideos, double rating, Integer totalLength) {
        this.seriesID = seriesID;
        this.name = name;
        this.showID = showID;
        this.noOfVideos = noOfVideos;
        this.rating = rating;
        this.totalLength = totalLength;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowID() {
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public Integer getNoOfVideos() {
        return noOfVideos;
    }

    public void setNoOfVideos(Integer noOfVideos) {
        this.noOfVideos = noOfVideos;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }
}
