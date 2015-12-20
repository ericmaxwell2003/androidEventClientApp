package event.credible.software.eventclientapp;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;

import event.credible.software.eventclientapp.remote.AuthenticationService;
import event.credible.software.eventclientapp.remote.EventService;
import event.credible.software.eventclientapp.remote.ServiceGenerator;
import event.credible.software.eventclientapp.remote.TokenHolder;

public class EventModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    public EventService eventServiceProvider(TokenHolder tokenHolder) {
        try {
            return ServiceGenerator.createService(EventService.class, tokenHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Provides
    public AuthenticationService authenticationServiceProvider() {
        try {
            return ServiceGenerator.createService(AuthenticationService.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
