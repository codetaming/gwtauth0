package org.codetaming.gwt.auth0.client;

import com.google.gwt.i18n.client.Constants;

public interface GwtAuth0Constants extends Constants {

    @DefaultStringValue("")
    String auth0ClientId();

    @DefaultStringValue("")
    String auth0Domain();
}
