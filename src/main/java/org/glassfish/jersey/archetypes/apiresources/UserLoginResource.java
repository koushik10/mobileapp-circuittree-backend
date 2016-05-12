package org.glassfish.jersey.archetypes.apiresources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.archetypes.services.ServiceFactory;

@Path("/userlogin")
public class UserLoginResource extends BaseApiResource{
    @GET
    @PermitAll
    @Produces
    public Response userLogin(@QueryParam("userId") String userId, @QueryParam("password") String password) throws Exception{
         final String sessionToken = ServiceFactory.getServiceFactory().getUserService().userLogIn(userId,password);
        if(sessionToken.equals("")){
            return genarate401Response("Unauthorized access");
        } else {
            return genarate200Response(sessionToken);
        }
    }


    public static void main(String args[]) throws Exception {
        new UserLoginResource().userLogin("akhila@gmail.com", "5678");
    }
    
}