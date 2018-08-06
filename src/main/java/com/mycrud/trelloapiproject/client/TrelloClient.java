package com.mycrud.trelloapiproject.client;

import com.mycrud.trelloapiproject.config.TrelloConfig;
import com.mycrud.trelloapiproject.domain.TrelloBoardDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Klasy, które są odpowiedzialne za udostępnianie połączeń
 * i wykonywania operacji na zewnętrznym API powszechnie nazywa się klientami połączeń.
 *
 * Utwórz klasę TrelloClient, który “wstrzyknie” wcześniej utworzonego beana klasy RestTemplate,
 * aby umożliwić wysyłanie żądań klasie TrelloClient:
 */

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrelloConfig trelloConfig;

    public URI urlBuilder(){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/ilozinski/boards")
                    .queryParam("key",trelloConfig.getTrelloApiKey())
                    .queryParam("token",trelloConfig.getTrelloApiToken())
                    .queryParam("fields","name,id") //
                    .build().encode().toUri();
        return url;
    }

    public List<TrelloBoardDto>getTrelloBoards() {

        TrelloBoardDto[] boardsResponse =
                restTemplate.getForObject(urlBuilder(), TrelloBoardDto[].class);

        if(boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        } else
            return new ArrayList<>();

    }
}
