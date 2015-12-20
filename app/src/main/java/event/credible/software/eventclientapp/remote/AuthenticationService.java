package event.credible.software.eventclientapp.remote;

import event.credible.software.eventclientapp.remote.dto.LoginDto;
import event.credible.software.eventclientapp.remote.dto.OAuthTokenDto;
import event.credible.software.eventclientapp.remote.dto.RegistrationDto;
import retrofit.http.Body;
import retrofit.http.POST;

public interface AuthenticationService {

    @POST("/register")
    RegistrationDto register(@Body RegistrationDto registrationDto);

    @POST("/login")
    OAuthTokenDto login(@Body LoginDto loginDto);

}
