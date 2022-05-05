package com.project;

import com.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movieUrl")
    private String getMovieUrl(){
        return movieService.getMovieURL("Tom&Jerry.mp4");
    }
}
