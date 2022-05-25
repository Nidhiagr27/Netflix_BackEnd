package com.project.controller;

import com.project.accessor.models.ShowsDTO;
import com.project.exceptions.DependencyFailureException;
import com.project.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/shows")
    public ResponseEntity findShowByname(@RequestParam("name") String name){
        try {
            List<ShowsDTO> listOfShows = showService.findShowByName(name);
            return ResponseEntity.ok(listOfShows);
        }
        catch(DependencyFailureException ex){
            System.out.println("Got dependency failure exception!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getCause().getMessage());
        }

    }
}
