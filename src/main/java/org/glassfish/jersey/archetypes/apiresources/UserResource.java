package org.glassfish.jersey.archetypes.apiresources;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.archetypes.model.User;
import org.glassfish.jersey.archetypes.model.Sensor;
import org.glassfish.jersey.archetypes.model.Tree;
import org.glassfish.jersey.archetypes.services.ServiceFactory;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/user")
public class UserResource extends BaseApiResource{

	    @GET
	    @Path("/{userId}")
	    @PermitAll
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response getUserByUserId(@PathParam("userId") String userId, @QueryParam("sessionToken")String sessionToken) throws Exception {
	        final User user = ServiceFactory.getServiceFactory().getUserService().getUserByUserId(userId, sessionToken);
	        if (user == null || user.getUserId() == null) {
	            return generate404Response("No user found");
	        } else {
	            return genarate200Response(user);
	        }

	    }

	 /*   @GET
	    @Path("/{userId}/{checkcomments}")
	    @PermitAll
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response checkCommentsAndRating(@PathParam("userId/checkcomments") String userId, @QueryParam("sessionToken")String sessionToken) throws Exception {
	        final User user = ServiceFactory.getServiceFactory().getUserService().checkCommentsAndRating(userId, sessionToken);
	        if (user == null || user.getUserId() == null) {
	            return generate404Response("No sensor found");
	        } else {
	            return genarate200Response(user);
	        }

	    }
	    
	    @POST
	    @PermitAll
	    @Consumes({"application/json"})
	    public Response addSensor(String sensorJson, @QueryParam("sessionToken") String sessionToken, @Context UriInfo uriInfo) throws Exception {
	        Gson gson = new Gson();
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	        Sensor sensor = (Sensor)mapper.readValue(sensorJson, Sensor.class);
	        int result = ServiceFactory.getServiceFactory().getSensorService().addSensor(sensor,sessionToken);
	        if(result!=-1){
	            return genarate200Response("SENSOR was inserted");
	        }else {
	            return genarate401Response("SENSOR was NOT installed");
	        }
	    }

	  */  
	    
}
