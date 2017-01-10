package org.codetaming.gwt.auth0.client;

public interface AuthenticationClient {

    void login();

    void logout();

    String getToken();

    boolean isLoggedIn();
}
