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

    public ShowsDTO(String showID, String name, ShowType type, Genre genre, ProfileType audience, double rating, Integer length) {
        this.showID = showID;
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.audience = audience;
        this.rating = rating;
        this.length = length;
    }

    public String getShowID() {
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShowType getType() {
        return type;
    }

    public void setType(ShowType type) {
        this.type = type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ProfileType getAudience() {
        return audience;
    }

    public void setAudience(ProfileType audience) {
        this.audience = audience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
