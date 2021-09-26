package service;

import com.google.gson.Gson;
import dto.BoardDto;


public class RestBoardService extends CommonService {

    private RestBoardService restBoardService;

    public RestBoardService getInstance() {
        if (restBoardService == null)
            restBoardService = new RestBoardService();

        return restBoardService;
    }


    public BoardDto getBoardById(String boardId) {
        return
            new Gson().fromJson(
                new CommonService()
                    .getNoParams(String.format(URI.GET_BY_BOARD_ID_URI, boardId))
                    .getBody().asString(), BoardDto.class);

    }


    public BoardDto createBoard(String boardName) {
        return
            new Gson().fromJson(
                new CommonService()
                    .postCreateBoard(URI.POST_BY_BOARD_NAME_URI, boardName)
                    .getBody().asString(), BoardDto.class);
    }


    public String  deleteBoardById(String boardId) {
        return new CommonService()
            .deleteBoard(String.format(URI.DELETE_BY_BOARD_ID_URI, boardId), boardId)
            .getBody().asString();
    }


    public BoardDto[] getAllBoard() {
        return
            new CommonService()
                .getAllBoards(URI.GET_ALL_BOARDS_URI)
                .getBody().as(BoardDto[].class);
    }

}
