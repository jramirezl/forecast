package com.finleap.forecast.util;

import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Component
public class ClientBuilderProviderImpl  implements ClientBuilderProvider{

    public Client getClient(){

        return ClientBuilder.newClient();
    }

}
