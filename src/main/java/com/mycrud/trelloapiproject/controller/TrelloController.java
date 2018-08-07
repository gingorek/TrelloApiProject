package com.mycrud.trelloapiproject.controller;

import com.mycrud.trelloapiproject.client.TrelloClient;
import com.mycrud.trelloapiproject.domain.CreatedTrelloCardDto;
import com.mycrud.trelloapiproject.domain.TrelloBoardDto;
import com.mycrud.trelloapiproject.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*") //Pozwala na komunikację serwera warstwy frontendowej z warstwą backendową.
@RestController
@RequestMapping("v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    //GET request
    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        return trelloBoards;

//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//            System.out.println("This board contains lists: ");
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//        });
    }

    //CREATE request
    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
}
