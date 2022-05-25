package com.project.accessor;

import com.project.accessor.models.AuthDTO;
import com.project.exceptions.DependencyFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class AuthAccessor {

    @Autowired
    private DataSource dataSource;


    /*public AuthDTO findByToken(String token) {
        String sql = "SELECT authId, token, userId from auth where token = ?";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, token);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new AuthDTO(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3));
            }
            return null;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }*/

    public void storeToken(final String userId,final String token) {
        String sql="INSERT INTO auth (authId,token,userId) values(?,?,?)";
        String uuid= UUID.randomUUID().toString(); //for authId in table auth
        try(Connection connection=dataSource.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,uuid);
            preparedStatement.setString(2,token);
            preparedStatement.setString(3,userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DependencyFailureException(e);
        }
    }
}
