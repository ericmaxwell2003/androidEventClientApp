package event.credible.software.eventclientapp.remote;

import event.credible.software.eventclientapp.remote.dto.EventSearchResultDto;
import retrofit.http.GET;
import retrofit.http.Query;

public interface EventService {

    @GET("/events")
    EventSearchResultDto fetchEvents(@Query("syncToken") String syncToken);

}
