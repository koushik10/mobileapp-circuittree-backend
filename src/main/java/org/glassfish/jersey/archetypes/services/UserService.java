package org.glassfish.jersey.archetypes.services;

import java.sql.SQLException;

import org.glassfish.jersey.archetypes.model.User;


/**
 * Created by koushiksekhar on 29/04/16.
 */

public interface UserService {

	public boolean isLogin(String sessionToken) throws SQLException;
    public String userLogIn(String userId, String password) throws SQLException, ClassNotFoundException;
    public User getUserByUserId(String userId, String sessionToken) throws Exception;
    public int addUser(User user, String sessionToken) throws Exception;
    public int addCommentsAndRating(User user, String sessionToken) throws Exception;
}
