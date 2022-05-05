package com.project.accessor;

import com.project.accessor.models.VideosDTO;
import com.project.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.websocket.DeploymentException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class VideoAccessor {

    @Autowired
    private DataSource dataSource;

    public VideosDTO getVideoById(String videoId){
        VideosDTO videosDTO =null;
        String query= "select * from videos where videoId=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,videoId);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                videosDTO=new VideosDTO();
                videosDTO.setVideoId(resultSet.getString(1));
                videosDTO.setName(resultSet.getString(2));
                videosDTO.setSeriesId(resultSet.getString(3));
                videosDTO.setShowId(resultSet.getString(4));
                videosDTO.setRating(resultSet.getDouble(5));
                //videosDTO.setReleaseDate(resultSet.getDate(6));
                videosDTO.setTotalLength(resultSet.getInt(7));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
            throw new DependencyFailureException(ex);
        }
        finally {
            return videosDTO;
        }
    }

    public boolean updateVideoRating(String videoId,double newRating){
        String query="UPDATE videos set rating =? where videoId=?";
        try(Connection connection= dataSource.getConnection()) {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setDouble(1,newRating);
            preparedStatement.setString(2,videoId);
           return preparedStatement.executeUpdate()==1? true:false; //returns integer how many rows are updated
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DependencyFailureException(e);
        }
    }
}
