package org.codetaming.gwt.auth0;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface Auth0ClientBundle extends ClientBundle {

    Auth0ClientBundle INSTANCE = GWT.create(Auth0ClientBundle.class);

    @Source("resource/js/lock.min.js")
    TextResource auth0();
}
