package org.codetaming.gwt.auth0.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface GwtAuth0ClientBundle extends ClientBundle {

    GwtAuth0ClientBundle INSTANCE = GWT.create(GwtAuth0ClientBundle.class);

    @Source("org/codetaming/gwt/auth0/client/resource/js/lock.min.js")
    TextResource auth0();
}
