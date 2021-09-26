package service;

import com.google.gson.Gson;
import dto.ListDto;

public class RestListService extends CommonService {

    private RestListService restListService;

    public RestListService getInstance() {
        if (restListService == null)
            restListService = new RestListService();

        return restListService;
    }


    public ListDto getListById(String listId) {
        return
            new Gson().fromJson(
                new CommonService()
                    .getNoParams(String.format(URI.GET_BY_LIST_ID_URI, listId))
                    .getBody().asString(), ListDto.class);

    }

    public ListDto createList(String boardId, String listName) {
        return
            new Gson().fromJson(
                new CommonService()
                    .postCreateList(URI.POST_BY_LIST_NAME_AND_BOARD_ID_URI, boardId, listName)
                    .getBody().asString(), ListDto.class);

    }

    public void moveListToAnotherBoard(String listId, String boardIdTo) {
        new CommonService()
            .putListToAnotherBoard(String.format(URI.PUT_TO_ANOTHER_BOARD_BY_LIST_ID_URI, listId), boardIdTo);
    }

   public void updateListName (String listId, String newListName) {
        new CommonService()
            .putNewListName(String.format(URI.PUT_NEW_NAME_BY_LIST_ID_URI, listId), newListName);
   }


}
