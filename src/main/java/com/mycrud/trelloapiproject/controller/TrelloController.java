package com.mycrud.trelloapiproject.controller;

import com.mycrud.trelloapiproject.client.TrelloClient;
import com.mycrud.trelloapiproject.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

            System.out.println("This board contain lists: ");

            trelloBoardDto.getLists().forEach(trelloListDto ->
                    System.out.println(trelloListDto.getName() + " - " + trelloListDto.getId()
                            + " - " + trelloListDto.isClosed()));
        });
    }
}
