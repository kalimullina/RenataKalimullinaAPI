package service;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.ListDto;
import io.restassured.response.Response;

public class RestListAssertions {

    private final ListDto listDto;

    public RestListAssertions(ListDto response) {
        this.listDto = response;
    }

    public void verifyDeletedListById() {

        Response response = new CommonService()
            .getNoParams(String.format(URI.GET_BY_LIST_ID_URI, listDto.getId()));

        assertThat(response.getStatusCode())
            .as("Wrong status code in verifyDeletedListById method")
            .isEqualTo(SC_NOT_FOUND);

        assertThat(response.getBody().asString())
            .as("Wrong response body in verifyDeletedListById method")
            .isEqualTo("model not found");
    }

    public RestListAssertions verifyListInBoardTo(String boardToId) {

        ListDto getRequestListDto = new RestListService()
            .getListById(listDto.getId());

        assertThat(getRequestListDto.getIdBoard())
            .as("Wrong response body in verifyListInBoardTo method")
            .isEqualTo(boardToId);

        return this;
    }

    public RestListAssertions verifyNewListNameById(String newListName, String oldListName) {

        ListDto getRequestListDto = new RestListService()
            .getListById(listDto.getId());

        assertThat(getRequestListDto.getName())
            .as("Wrong with old list name in verifyNewListNameById method")
            .isNotEqualTo(oldListName);

        assertThat(getRequestListDto.getName())
            .as("Wrong with new list name in verifyNewListNameById method")
            .isEqualTo(newListName);

        return this;
    }

}
