import dto.BoardDto;
import dto.ListDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.RestBoardAssertions;
import service.RestBoardService;
import service.RestListAssertions;
import service.RestListService;

public class CheckBoardAndList {

    @BeforeMethod
    void beforeMethod() {
        BoardDto[] allBoards = RestBoardService.getInstance().getAllBoard();
        if (allBoards.length != 0) {
            for (BoardDto i : allBoards) {
                RestBoardService.getInstance()
                                .deleteBoardById(i.getId());
            }
        }
    }

    @Test
    void getBoardByIdTest() {
        String createdBoardName = "board for GetTest";

        BoardDto createdBoard = RestBoardService.getInstance()
            .createBoard(createdBoardName);

        new RestBoardAssertions(createdBoard)
            .verifyBoardById();
    }

    @Test
    void deleteBoardByIdTest() {
        String createdBoardName = "board for deleteTest";

        BoardDto createdBoard = RestBoardService.getInstance()
            .createBoard(createdBoardName);

        RestBoardService.getInstance()
            .deleteBoardById(createdBoard.getId());

        new RestBoardAssertions(createdBoard)
            .verifyDeletedBoard()
            .verifyDeleteNonExistentBoard();
    }

    @Test
    void getListInDeletedBoardTest() {
        String createdBoardName = "board for getListInDeletedBoardTest";
        String createdListName = "list for getListInDeletedBoardTest";

        BoardDto createdBoard = RestBoardService.getInstance()
            .createBoard(createdBoardName);

        ListDto createdList = RestListService.getInstance()
            .createList(createdBoard.getId(), createdListName);

        RestBoardService.getInstance()
            .deleteBoardById(createdBoard.getId());

        new RestListAssertions(createdList)
            .verifyDeletedListById();
    }

    @Test
    void getMovedListTest() {
        String createdBoardNameFrom = "board (from) for getMovedListTest";
        String createdBoardNameTo = "board (to) for getMovedListTest";
        String createdListName = "list for getMovedListTest";

        BoardDto createdBoardFrom = RestBoardService.getInstance()
            .createBoard(createdBoardNameFrom);
        BoardDto createdBoardTo = RestBoardService.getInstance()
            .createBoard(createdBoardNameTo);
        ListDto createdList = RestListService.getInstance()
            .createList(createdBoardFrom.getId(), createdListName);

        RestListService.getInstance()
            .moveListToAnotherBoard(createdList.getId(), createdBoardTo.getId());

        new RestListAssertions(createdList)
            .verifyListInBoardTo(createdBoardTo.getId());
    }

    @Test
    void updateListNameTest() {
        String createdBoardName = "board for updateListNameTest";
        String oldListName = "list for updateListNameTest";
        String newListName = "new list name for updateListNameTest";

        BoardDto createdBoard = RestBoardService.getInstance()
            .createBoard(createdBoardName);

        ListDto createdList = RestListService.getInstance()
            .createList(createdBoard.getId(), oldListName);

        RestListService.getInstance()
            .updateListName(createdList.getId(), newListName);

        new RestListAssertions(createdList)
            .verifyNewListNameById(newListName, oldListName);
    }
}
