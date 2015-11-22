package event.credible.software.eventclientapp.remote;

import java.util.List;

import event.credible.software.eventclientapp.domain.Event;
import event.credible.software.eventclientapp.remote.dto.LoginDto;
import event.credible.software.eventclientapp.remote.dto.OAuthTokenDto;
import event.credible.software.eventclientapp.remote.dto.RegistrationDto;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface EventService {

    @POST("/register")
    RegistrationDto register(@Body RegistrationDto registrationDto);

    @POST("/goToLogin")
    OAuthTokenDto login(@Body LoginDto loginDto);

    @GET("/events")
    List<Event> fetchEvents();

    @GET("/event/{id}")
    Event fetchDetail(@Path("id") String eventId);

}
