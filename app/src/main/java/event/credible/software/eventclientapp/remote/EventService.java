package event.credible.software.eventclientapp.remote;

import java.util.List;

import event.credible.software.eventclientapp.domain.Event;
import event.credible.software.eventclientapp.remote.dto.EventDto;
import event.credible.software.eventclientapp.remote.dto.EventSearchResultDto;
import event.credible.software.eventclientapp.remote.dto.LoginDto;
import event.credible.software.eventclientapp.remote.dto.OAuthTokenDto;
import event.credible.software.eventclientapp.remote.dto.RegistrationDto;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface EventService {

    @GET("/events")
    EventSearchResultDto fetchEvents(@Query("syncToken") String syncToken);

}
