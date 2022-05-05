package com.project.service;

import com.project.accessor.VideoAccessor;
import com.project.accessor.models.VideosDTO;
import com.project.exceptions.VideoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoService {

    @Autowired
    private VideoAccessor videoAccessor;

    public boolean updateVideoRating(String videoId, double newRating){
        VideosDTO videosDTO=videoAccessor.getVideoById(videoId);
      if(videosDTO ==null){
          return videoAccessor.updateVideoRating(videoId,newRating);
      }
      else{
          throw new VideoNotFoundException(videoId);
      }
    }
}
