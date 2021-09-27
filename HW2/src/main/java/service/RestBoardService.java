package service;

import com.google.gson.Gson;
import dto.BoardDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class RestBoardService extends CommonService {

    private static RestBoardService restBoardService;

    private RestBoardService() {

    }

    public static RestBoardService getInstance() {
        if (restBoardService == null)
            restBoardService = new RestBoardService();

        return restBoardService;
    }

    public Response getBoardByIdResponse(String boardId) {
        return
            new CommonService()
                .getNoParams(String.format(URI.GET_BY_BOARD_ID_URI, boardId));
    }

    public BoardDto createBoard(String boardName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", boardName);

        return
            new Gson().fromJson(
                new CommonService()
                    .postWithParams(URI.POST_BY_BOARD_NAME_URI, params)
                    .getBody().asString(), BoardDto.class);
    }

    public String  deleteBoardById(String boardId) {
        return new CommonService()
            .deleteNoParams(String.format(URI.DELETE_BY_BOARD_ID_URI, boardId))
            .getBody().asString();
    }

    public BoardDto[] getAllBoard() {
        return
            new CommonService()
                .getNoParams(URI.GET_ALL_BOARDS_URI)
                .getBody().as(BoardDto[].class);
    }
}
