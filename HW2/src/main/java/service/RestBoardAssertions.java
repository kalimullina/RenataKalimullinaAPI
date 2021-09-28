package service;

import dto.BoardDto;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class RestBoardAssertions {

    private final BoardDto boardDto;

    public RestBoardAssertions(BoardDto response) {
        this.boardDto = response;
    }

    public void verifyBoardById() {

        Response response = RestBoardService.getInstance()
            .getBoardByIdResponse(boardDto.getId());

        assertThat(response.getStatusCode())
            .as("Wrong status code in verifyBoardById method")
            .isEqualTo(SC_OK);
    }

    public RestBoardAssertions verifyDeletedBoard() {

        Response response = RestBoardService.getInstance()
            .getBoardByIdResponse( boardDto.getId());

        assertThat(response.getStatusCode())
            .as("Wrong status code in verifyDeletedBoard method")
            .isEqualTo(SC_NOT_FOUND);

        return this;
    }

    public RestBoardAssertions verifyDeleteNonExistentBoard() {

        String errorMessage = RestBoardService.getInstance()
            .deleteBoardById(boardDto.getId());

        assertThat(errorMessage)
            .as("Wrong invalid message in verifyDeleteNonExistentBoard method")
            .isEqualTo("The requested resource was not found.");

        return this;
    }
}
