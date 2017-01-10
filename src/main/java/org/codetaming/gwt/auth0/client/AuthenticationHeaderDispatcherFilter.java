package org.codetaming.gwt.auth0.client;

import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.Inject;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DispatcherFilter;

public class AuthenticationHeaderDispatcherFilter implements DispatcherFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final AuthenticationClient authenticationClient;

    @Inject
    public AuthenticationHeaderDispatcherFilter(final AuthenticationClient authenticationClient) {
        this.authenticationClient = authenticationClient;
    }

    @Override
    public boolean filter(Method method, RequestBuilder builder) {
        builder.setHeader(AUTHORIZATION_HEADER, "Bearer " + authenticationClient.getToken());
        return true;
    }

}
