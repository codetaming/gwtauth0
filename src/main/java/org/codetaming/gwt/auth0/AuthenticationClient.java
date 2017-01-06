package org.codetaming.gwt.auth0;

public interface AuthenticationClient {

    void login();

    void logout();

    String getToken();

    boolean isLoggedIn();
}
