package com.canoo.dp.impl.security;

import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.client.security.Security;
import com.canoo.platform.core.PlatformConfiguration;
import com.canoo.platform.core.http.HttpURLConnectionHandler;
import com.canoo.platform.core.http.spi.RequestHandlerProvider;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.INTERNAL;

@API(since = "0.19.0", status = INTERNAL)
public class SecurityRequestHandlerProvider implements RequestHandlerProvider {

    @Override
    public HttpURLConnectionHandler getHandler(final PlatformConfiguration configuration) {
        final Security security = PlatformClient.getService(Security.class);
        return new KeycloakRequestHandler(security);
    }
}
