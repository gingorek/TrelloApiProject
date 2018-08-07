package com.mycrud.trelloapiproject.client;

import com.mycrud.trelloapiproject.config.TrelloConfig;
import com.mycrud.trelloapiproject.domain.CreatedTrelloCardDto;
import com.mycrud.trelloapiproject.domain.TrelloBoardDto;
import com.mycrud.trelloapiproject.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 *Klasy, które są odpowiedzialne za udostępnianie połączeń
 * i wykonywania operacji na zewnętrznym API powszechnie nazywa się klientami połączeń.
 *
 * Utwórz klasę TrelloClient, który “wstrzyknie” wcześniej utworzonego beana klasy RestTemplate,
 * aby umożliwić wysyłanie żądań klasie TrelloClient:
 */

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

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

        try {
            Optional<TrelloBoardDto[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(urlBuilder(),TrelloBoardDto[].class));
            return Arrays.asList(boardsResponse.orElse(new TrelloBoardDto[0]));
        } catch(RestClientException e){
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key",trelloConfig.getTrelloApiKey())
                .queryParam("token",trelloConfig.getTrelloApiToken())
                .queryParam("name",trelloCardDto.getName()) // tu opisuje do jakich parametrow chce uzyskac dostep
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();

        CreatedTrelloCardDto cardResponse = restTemplate.postForObject(url,null,CreatedTrelloCardDto.class);

        return cardResponse;

    }
}
