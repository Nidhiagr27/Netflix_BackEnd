package com.project.accessor.models;

import lombok.Data;

@Data
public class PersonShowsDTO {
    private String personID;
    private String showID;
    private PersonRole role;
    private double weightage;

    public PersonShowsDTO(String personID, String showID, PersonRole role, double weightage) {
        this.personID = personID;
        this.showID = showID;
        this.role = role;
        this.weightage = weightage;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getShowID() {
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
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
