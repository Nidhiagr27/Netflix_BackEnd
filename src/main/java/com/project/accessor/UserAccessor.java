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

    public UsersDTO getUser(String query,String email,String userID){
        UsersDTO usersDTO=null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(userID!=null) {
                preparedStatement.setString(1, userID);
            }else{
                preparedStatement.setString(1,email);
            }
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
        finally {
            return usersDTO;
        }
    }

    public UsersDTO getUserByUserID(String userID) {
        String query = "SELECT userId,name,email,password,phone,state FROM users where userId=?";
        return getUser(query,null,userID);
    }

    public UsersDTO getUserByEmail(String email)  {
        String query= "SELECT userId,name,email,password,phone,state FROM users where email=?";
        return getUser(query,email,null);
    }
}
