package org.glassfish.jersey.archetypes.services;

import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.DatabaseConnectionProvider;
//import org.glassfish.jersey.archetypes.model.Sensor;
import org.glassfish.jersey.archetypes.model.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Created by koushiksekhar on 29/04/16.
 */

public class UserServiceImpl implements UserService{

	private final DatabaseConnectionProvider db;
    public UserServiceImpl(DatabaseConnectionProvider db){ this.db = db;}

    public String userLogIn(String userId, String password) throws SQLException, ClassNotFoundException {
        String stoken = "";
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("select * from USER where userId=? and password =?");
        preparedStatement.setString(1,userId);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            stoken = resultSet.getString(1);
            PreparedStatement updatePreparedStatement = db.getConnection().prepareStatement("Update USER set sessionToken = ? where userId =?");
            updatePreparedStatement.setString(1,stoken);
            updatePreparedStatement.setString(2,userId);
            updatePreparedStatement.executeUpdate();
            updatePreparedStatement.close();
        }
        resultSet.close();
        return stoken;
    }

    public boolean isLogin(String sessionToken) throws SQLException {
        //String sToken =" ";
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * from USER where sessionToken = ?");
        preparedStatement.setString(1,sessionToken);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }


    public static void main(String args[]) throws Exception {
        new UserServiceImpl(DatabaseConnectionProvider.getDatabaseProvider()).userLogIn("akhila@gmail.com", "5678");
    }
	

public User getUserByUserId(String userId, String sessionToken)throws Exception{
    if(!ServiceFactory.getServiceFactory().getUserService().isLogin(sessionToken)){
        return null;
    }
    ResultSet resultSet = db.select("select * from USER where userId="+userId+";");
    User user = new User();
    if (resultSet.next()){
    	user.setUserId(resultSet.getString(1));
    	user.setFirstName(resultSet.getString(2));
    	user.setLastName(resultSet.getString(3));
    	user.setAddress(resultSet.getString(4));
    	user.setPhoneNumber(resultSet.getString(5));
    	user.setPassword(resultSet.getString(6));
    	user.setSessionToken(resultSet.getString(7));
    }
    return user;
}


public int addUser(User user, String sessionToken) throws Exception {
    if (!ServiceFactory.getServiceFactory().getUserService().isLogin(sessionToken)) {
        return -1;
    }
    PreparedStatement preparedStatement = db.getConnection().prepareStatement("Insert into USER values(?,?,?,?,?,?)");
    preparedStatement.setString(1, user.getUserId());
    preparedStatement.setString(2, user.getFirstName());
    preparedStatement.setString(3, user.getLastName());
    preparedStatement.setString(4, user.getAddress());
    preparedStatement.setString(5, user.getPhoneNumber());
    preparedStatement.setString(6, user.getPassword());
    int result = preparedStatement.executeUpdate();
    preparedStatement.close();
    return result;
}
public int addCommentsAndRating(User user, String sessionToken) throws Exception {
    if (!ServiceFactory.getServiceFactory().getUserService().isLogin(sessionToken)) {
        return -1;
    }
    PreparedStatement preparedStatement = db.getConnection().prepareStatement("Insert into COMMENTSANDRATING values(?,?,?)");
    preparedStatement.setString(1, user.getUserId());
    preparedStatement.setString(2, user.getComments());
    preparedStatement.setString(3, user.getRating()); 
    int result = preparedStatement.executeUpdate();
    preparedStatement.close();
    return result;
}
/*
public User checkCommentsAndRating(String userId, String sessionToken) throws Exception {
    if (!ServiceFactory.getServiceFactory().getUserService().isLogin(sessionToken)) {
        return null;
    }
    //JSONArray array = new JSONArray();
    //Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ResultSet resultSet = db.select("select * from USER where userId="+userId+";");
    User user = new User();
    if (resultSet.next()){
    	user.setUserId(resultSet.getString(1));
    	user.setFirstName(resultSet.getString(2));
    	user.setLastName(resultSet.getString(3));
    }
    return user;
}
*/
}
