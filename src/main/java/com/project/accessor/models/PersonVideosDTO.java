package com.project.accessor.models;

import lombok.Data;

@Data
public class PersonVideosDTO {
    private String personID;
    private String videoID;
    private PersonRole role;
    private double weightage;

    public PersonVideosDTO(String personID, String videoID, PersonRole role, double weightage) {
        this.personID = personID;
        this.videoID = videoID;
        this.role = role;
        this.weightage = weightage;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public double getWeightage() {
        return weightage;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }
}
