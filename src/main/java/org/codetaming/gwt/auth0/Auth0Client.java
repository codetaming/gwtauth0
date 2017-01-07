package org.codetaming.gwt.auth0;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.storage.client.Storage;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import java.util.logging.Logger;

public class Auth0Client {

    private static final String ID_TOKEN = "id_token";

    private static final Logger LOGGER = Logger.getLogger(Auth0Client.class.getName());

    private final Auth0Constants auth0Constants = GWT.create(Auth0Constants.class);

    private Storage localStorage = null;

    private EventBus eventBus;

    @Inject
    public Auth0Client(final EventBus eventBus) {
        this.eventBus = eventBus;
        init();
    }

    public void init() {
        injectAuth0Script();
        setupLocalStorage();
        createLock(auth0Constants.auth0ClientId(), auth0Constants.auth0Domain());
    }

    private final native void createLock(String auth0ClientId, String auth0Domain) /*-{
        $wnd.lock = new $wnd.Auth0Lock(auth0ClientId, auth0Domain,
            {
                auth: {
                    redirect: false
                }
            }
        );
        var self = this;
        var callback = $entry(function (authResult) {
            self.@org.codetaming.gwt.auth0.Auth0Client::loginCallback(Ljava/lang/String;)(
                authResult.idToken);
        });
        $wnd.lock.on("authenticated", callback);
    }-*/;

    private void setupLocalStorage() {
        localStorage = Storage.getLocalStorageIfSupported();
    }

    private void injectAuth0Script() {
        LOGGER.info("Loading Auth0");
        ScriptInjector.fromString(Auth0ClientBundle.INSTANCE.auth0().getText())
                .setWindow(ScriptInjector.TOP_WINDOW)
                .inject();
    }

    public void login() {
        nativeLogin();
    }

    public void logout() {
        if (localStorage != null) {
            localStorage.removeItem(ID_TOKEN);
            performLogoutActions();
        }
    }

    protected void performLogoutActions() {
        LOGGER.info("Logout");
    }

    public String getToken() {
        if (localStorage != null) {
            return localStorage.getItem(ID_TOKEN);
        }
        return "";
    }

    public boolean isLoggedIn() {
        if (localStorage != null) {
            return localStorage.getItem("id_token") != null;
        }
        return false;
    }

    public void retrieveProfile() {
        if (localStorage != null) {
            String idToken = localStorage.getItem("id_token");
            nativeRetrieveProfile(idToken);
        }
    }

    private final native void nativeRetrieveProfile(String idToken) /*-{
        $wnd.lock.getProfile(idToken, function (err, profile) {
            if (err) {
                return alert('There was an error getting the profile: ' + err.message);
            }
            this.@org.codetaming.gwt.auth0.Auth0Client::storeProfile(Ljava/lang/String;)(
                profile.name);
        });
    }-*/;


    private void storeProfile(String profileStr) {
        if (localStorage != null) {
            localStorage.setItem("profile", profileStr);
        }
    }

    private void loginCallback(String idToken) {
        if (localStorage != null) {
            localStorage.setItem("id_token", idToken);
            performLoginAction();

        }
    }

    protected void performLoginAction() {
        LOGGER.info("Logout");
        retrieveProfile();
    }

    private final native void nativeLogin() /*-{
        $wnd.lock.show();
    }-*/;
}
