package aor.paj.service;

import aor.paj.bean.NotificationBean;
import aor.paj.bean.UserBean;

import aor.paj.dto.NotificationDto;
import aor.paj.dto.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Path("/notification")
public class NotificationService {

    @Inject
    NotificationBean notificationBean;
    @Inject
    UserBean userBean;

    private static final Logger logger = LogManager.getLogger(NotificationBean.class);


    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotifications(@HeaderParam("token") String token) {
        User user = userBean.getUserByToken(token);
        if(user != null){
            List<NotificationDto> notifications = notificationBean.getNotificationsByToken(token);

            return Response.ok(notifications).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
        }
    }

    @PUT
    @Path("/read")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response markedAsRead(@HeaderParam("token") String token) {
        User user = userBean.getUserByToken(token);
        if(user != null){
            if(notificationBean.markNotificationAsRead(token)){
                logger.info("Notification marked as read by user: " + user.getUsername() + " at " + System.currentTimeMillis());

                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Notification not found").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
        }
    }

    @GET
    @Path("/unread")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnreadNotifications(@HeaderParam("token") String token) {
        User user = userBean.getUserByToken(token);
        if(user != null){
            List<NotificationDto> notifications = notificationBean.getUnreadNotificationsByToken(token);

            return Response.ok(notifications).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not logged in").build();
        }
    }
}
