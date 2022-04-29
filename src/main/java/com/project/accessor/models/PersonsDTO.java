package com.project.accessor.models;

import lombok.Data;

@Data
public class PersonsDTO {
    private String personId;
    private String name;
    private String email;

    public PersonsDTO(String personId, String name, String email) {
        this.personId = personId;
        this.name = name;
        this.email = email;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
