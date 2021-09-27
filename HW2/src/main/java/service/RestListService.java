package service;

import com.google.gson.Gson;
import dto.ListDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class RestListService extends CommonService {

    private static RestListService restListService;

    private RestListService() {

    }

    public static RestListService getInstance() {
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

    public Response getListByIdResponse(String listId) {
        return
            new CommonService()
                .getNoParams(String.format(URI.GET_BY_LIST_ID_URI, listId));
    }

    public ListDto createList(String boardId, String listName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idBoard", boardId);
        params.put("name", listName);

        return
            new Gson().fromJson(
                new CommonService()
                    .postWithParams(URI.POST_BY_LIST_NAME_AND_BOARD_ID_URI, params)
                    .getBody().asString(), ListDto.class);

    }

    public void moveListToAnotherBoard(String listId, String boardIdTo) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("value", boardIdTo);

        new CommonService()
            .putWithParams(String.format(URI.PUT_TO_ANOTHER_BOARD_BY_LIST_ID_URI, listId), params);
    }

   public void updateListName (String listId, String newListName) {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("name", newListName);

        new CommonService()
            .putWithParams(String.format(URI.PUT_NEW_NAME_BY_LIST_ID_URI, listId), params);
   }
}
