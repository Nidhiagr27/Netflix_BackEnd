package com.project.service;

import com.project.accessor.ShowAccessor;
import com.project.accessor.models.ShowsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowService {

    @Autowired
    private ShowAccessor showAccessor;

    public List<ShowsDTO> findShowByName(String name){
       return showAccessor.findShowByName(name);
    }
}
