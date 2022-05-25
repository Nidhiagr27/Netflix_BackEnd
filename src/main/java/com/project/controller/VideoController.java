package com.project.controller;

import com.project.exceptions.DependencyFailureException;
import com.project.exceptions.VideoNotFoundException;
import com.project.security.Roles;
import com.project.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Secured({Roles.Customer})
    @PutMapping("/video/{videoId}")
    public ResponseEntity updateVideoRating(@PathVariable String videoId, @RequestParam("newRating") double newRating){
      try{
          videoService.updateVideoRating(videoId,newRating);
       return ResponseEntity.status(HttpStatus.OK).body(true);
      }
      catch(VideoNotFoundException ex){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
      }
      catch(DependencyFailureException ex){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getCause().getMessage());
      }
    }
}
