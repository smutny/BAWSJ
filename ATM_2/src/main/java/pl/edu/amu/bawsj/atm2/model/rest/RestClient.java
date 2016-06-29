package pl.edu.amu.bawsj.atm2.model.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.net.URI;

public class RestClient {
    public static URI BASE_URI = URI.create("http://localhost:8080/11.JPA/rest");
    private Client client;

    public RestClient() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(config);
    }

    public Client getClient() {
        return client;
    }
}
