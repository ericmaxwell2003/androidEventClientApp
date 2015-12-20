package event.credible.software.eventclientapp.remote;

import javax.inject.Singleton;

import event.credible.software.eventclientapp.remote.dto.OAuthTokenDto;

@Singleton
public class TokenHolder {

    private OAuthTokenDto token;

    public OAuthTokenDto getToken() {
        return token;
    }

    public void setToken(OAuthTokenDto token) {
        this.token = token;
    }
}
