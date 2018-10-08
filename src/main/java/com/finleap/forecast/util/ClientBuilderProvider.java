package com.finleap.forecast.util;

import javax.ws.rs.client.Client;

public interface ClientBuilderProvider {
    Client getClient();
}
