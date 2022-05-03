package com.project.accessor;

import com.project.accessor.models.Genre;
import com.project.accessor.models.ProfileType;
import com.project.accessor.models.ShowType;
import com.project.accessor.models.ShowsDTO;
import com.project.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShowAccessor {

    @Autowired
    private DataSource dataSource;

    public List<ShowsDTO> findShowByName(String name){
        ShowsDTO showsDTO=new ShowsDTO();
     String query= "SELECT * from shows where name like '%"+name+"%' ";
     List<ShowsDTO> listOfShows =new ArrayList<>();
     try(Connection connection= dataSource.getConnection()){
         PreparedStatement preparedStatement= connection.prepareStatement(query);
         /*preparedStatement.setString(1,name);*/
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
          showsDTO.setShowID(resultSet.getString(1));
          showsDTO.setName(resultSet.getString(2));
          showsDTO.setType(ShowType.valueOf(resultSet.getString(3)));
          showsDTO.setGenre(Genre.valueOf(resultSet.getString(4)));
          showsDTO.setAudience(ProfileType.valueOf(resultSet.getString(5)));
          showsDTO.setRating(resultSet.getDouble(6));
          showsDTO.setLength(resultSet.getInt(7));
          listOfShows.add(showsDTO);
        }
         return listOfShows;
     }
     catch(SQLException ex){
         ex.printStackTrace();
         throw new DependencyFailureException(ex);
     }

    }
}
