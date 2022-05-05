package com.project.exceptions;

public class VideoNotFoundException extends RuntimeException{
    private String videoId;

    public VideoNotFoundException(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String getMessage(){
        return "Video Not Found!!!";
    }
}
