package com.mycrud.trelloapiproject.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Tu przetrzymuje informacje o URI dla endpointow wystawianych przez trello
 * oraz autentykacje dla Trello.
 */
@Component
@Getter
public class TrelloConfig {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.api.key}")
    private String trelloApiKey;

    @Value("${trello.api.token}")
    private String trelloApiToken;

    @Value("${trello.api.username}")
    private String username;
}
