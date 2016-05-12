package org.glassfish.jersey.archetypes.model;

/**
 * Created by koushikSekhar on 29/04/16.
 */
public class User {
	
	  private String userId;
	    private String firstName;
	    private String lastName;
	    private String address;
	    private String phoneNumber;
	    private String password;
	    private String sessionToken;
	    private String comments;
	    private String rating;
	    private int treeId;

public String getUserId() {
    return userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getAddress() {
    return address;
}

public void setAddress (String address) {
    this.address = address;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getSessionToken() {
    return sessionToken;
}

public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
}

public String getComments() {
    return comments;
}

public void setComments(String comments) {
    this.comments = comments;
}

public String getRating() {
    return rating;
}

public void setRating(String rating) {
    this.rating = rating;
}

public int getTreeId() {
    return treeId;
}

public void setTreeId(int treeId) {
   this.treeId = treeId;
}

}





