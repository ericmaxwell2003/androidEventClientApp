package event.credible.software.eventclientapp.activity.remote;

import java.util.List;

import event.credible.software.eventclientapp.activity.remote.dto.EventDto;
import event.credible.software.eventclientapp.activity.remote.dto.LoginDto;
import event.credible.software.eventclientapp.activity.remote.dto.OAuthTokenDto;
import event.credible.software.eventclientapp.activity.remote.dto.RegistrationDto;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface EventService {

    @POST("/register")
    RegistrationDto register(@Body RegistrationDto registrationDto);

    @POST("/login")
    OAuthTokenDto login(@Body LoginDto loginDto);

    @GET("/events")
    List<EventDto> fetchEvents();

    @GET("/event/{id}")
    EventDto fetchDetail(@Path("id") String eventId);

}
