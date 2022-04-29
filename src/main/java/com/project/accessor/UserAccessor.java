package com.project.accessor;

import com.project.accessor.models.UserAccountState;
import com.project.accessor.models.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserAccessor {

    @Autowired
    private DataSource dataSource;

    public boolean updatePassword(String userID, String newPassword){
        String query="update users set password=? where userID=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,userID);
            return  preparedStatement.executeUpdate() ==1? true:false;
        //executeUpdate returns integer, it tells how many rows it has updated

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public UsersDTO getUserByUserID(String userID) {
        UsersDTO usersDTO = null;
        String query = "SELECT userId,name,email,password,phone,state FROM users where userId=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                usersDTO=new UsersDTO();
                usersDTO.setUserID(result.getString(1));
                usersDTO.setName(result.getString(2));
                usersDTO.setEmail(result.getString(3));
                usersDTO.setPassword(result.getString(4));
                usersDTO.setPhone(result.getString(5));
                usersDTO.setState(UserAccountState.valueOf(result.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersDTO;
    }

    public UsersDTO getUserByEmail(String email)  {
        UsersDTO usersDTO=null;
        String query= "SELECT userId,name,email,password,phone,state FROM users where email=?";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet result=preparedStatement.executeQuery();
            if(result.next()){
                usersDTO=new UsersDTO();
                usersDTO.setUserID(result.getString(1));
                usersDTO.setName(result.getString(2));
                usersDTO.setEmail(result.getString(3));
                usersDTO.setPassword(result.getString(4));
                usersDTO.setPhone(result.getString(5));
                usersDTO.setState(UserAccountState.valueOf(result.getString(6)));



            }

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            return usersDTO;
        }

    }
}
