package com.project.accessor;

import com.project.accessor.models.UserAccountState;
import com.project.accessor.models.UserRole;
import com.project.accessor.models.UsersDTO;
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

    public UsersDTO getUser(String query,String email,String userID, String phone){
        UsersDTO usersDTO=null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if( phone==null && email==null) {
                preparedStatement.setString(1, userID);
            }else if(userID==null && phone==null){
                preparedStatement.setString(1,email);
            }
            else{
                preparedStatement.setString(1,phone);
            }
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                usersDTO=UsersDTO.builder()
                        .userId(result.getString(1))
                        .name(result.getString(2))
                        .email(result.getString(3))
                        .password(result.getString(4))
                        .phone(result.getString(5))
                        .state(UserAccountState.valueOf(result.getString(6)))
                        .role(UserRole.valueOf(result.getString(7)))
                        .build();


                /**When @Data was used below code was used **/
                /*usersDTO=new UsersDTO();
                usersDTO.setUserId(result.getString(1));
                usersDTO.setName(result.getString(2));
                usersDTO.setEmail(result.getString(3));
                usersDTO.setPassword(result.getString(4));
                usersDTO.setPhone(result.getString(5));
                usersDTO.setState(UserAccountState.valueOf(result.getString(6)));
                usersDTO.setRole(UserRole.valueOf(result.getString(7)));*/


                return usersDTO;
            }
            return usersDTO;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DependencyFailureException(e);
        }

    }

    public UsersDTO getUserByUserID(String userID) {
        String query = "SELECT userId,name,email,password,phone,state FROM users where userId=?";
        return getUser(query,null,userID,null);
    }

    public UsersDTO getUserByEmail(final String email)  {
        String query= "SELECT userId,name,email,password,phone,state,role FROM users where email=?";
        return getUser(query,email,null,null);
    }

    public UsersDTO getUserByPhone(final String phone){
        String query="SELECT userId,name,email,password,phone,state,role FROM users where phone=?" ;
        return getUser(query,null,null, phone);
    }

    public void addNewUser(final String name, final String email,final String password,final String phone,
                           final UserAccountState userAccountState,final UserRole userRole){
        String query="INSERT INTO users values (?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement pstmt= connection.prepareStatement(query);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2,name);
            pstmt.setString(3,email);
            pstmt.setString(4,password);
            pstmt.setString(5,phone);
            pstmt.setString(6,userAccountState.name());
            pstmt.setString(7, userRole.name());
            pstmt.execute();
        }
        catch(SQLException ex){
            ex.printStackTrace();
         throw new DependencyFailureException(ex);
        }
    }
}
