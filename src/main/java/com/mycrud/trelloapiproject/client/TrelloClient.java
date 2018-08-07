package com.mycrud.trelloapiproject.client;

import com.mycrud.trelloapiproject.config.TrelloConfig;
import com.mycrud.trelloapiproject.domain.CreatedTrelloCard;
import com.mycrud.trelloapiproject.domain.TrelloBoardDto;
import com.mycrud.trelloapiproject.domain.TrelloCardDto;
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
                    .queryParam("fields","name,id") // tu opisuje do jakich parametrow chce uzyskac dostep
                    .queryParam("lists", "all")
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

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key",trelloConfig.getTrelloApiKey())
                .queryParam("token",trelloConfig.getTrelloApiToken())
                .queryParam("name",trelloCardDto.getName()) // tu opisuje do jakich parametrow chce uzyskac dostep
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("listId", trelloCardDto.getListId())
                .build().encode().toUri();

        CreatedTrelloCard cardResponse = restTemplate.postForObject(url,null,CreatedTrelloCard.class);

        return cardResponse;


    }
}
