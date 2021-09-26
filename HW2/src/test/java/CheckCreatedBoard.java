import dto.BoardDto;
import dto.ListDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.RestBoardAssertions;
import service.RestBoardService;
import service.RestListAssertions;
import service.RestListService;

public class CheckCreatedBoard {

    @BeforeMethod
    void beforeMethod() {

        BoardDto[] allBoards = new RestBoardService()
            .getAllBoard();

        if (allBoards.length != 0) {
            for (BoardDto i : allBoards) {
                new RestBoardService()
                    .deleteBoardById(i.getId());
            }
        }
    }

    @Test
    void getBoardByIdTest() {

        String createdBoardName = "board for GetTest";

        BoardDto createdBoard = new RestBoardService()
            .createBoard(createdBoardName);

        new RestBoardAssertions(createdBoard)
            .verifyBoardById();
    }

    @Test
    void deleteBoardByIdTest() {
        String createdBoardName = "board for deleteTest";

        BoardDto createdBoard = new RestBoardService()
            .createBoard(createdBoardName);

        new RestBoardService()
            .deleteBoardById(createdBoard.getId());

        new RestBoardAssertions(createdBoard)
            .verifyDeletedBoard()
            .verifyDeleteNonExistentBoard();
    }

    @Test
    void getListInDeletedBoardTest() {
        String createdBoardName = "board for getListInDeletedBoardTest";
        String createdListName = "list for getListInDeletedBoardTest";

        BoardDto createdBoard = new RestBoardService()
            .createBoard(createdBoardName);

        ListDto createdList = new RestListService()
            .createList(createdBoard.getId(), createdListName);

        new RestBoardService()
            .deleteBoardById(createdBoard.getId());

        new RestListAssertions(createdList)
            .verifyDeletedListById();
    }

    @Test
    void getMovedListTest() {
        String createdBoardNameFrom = "board (from) for getMovedListTest";
        String createdBoardNameTo = "board (to) for getMovedListTest";
        String createdListName = "list for getMovedListTest";

        BoardDto createdBoardFrom = new RestBoardService()
            .createBoard(createdBoardNameFrom);
        BoardDto createdBoardTo = new RestBoardService()
            .createBoard(createdBoardNameTo);
        ListDto createdList = new RestListService()
            .createList(createdBoardFrom.getId(), createdListName);

        new RestListService()
            .moveListToAnotherBoard(createdList.getId(), createdBoardTo.getId());

        new RestListAssertions(createdList)
            .verifyListInBoardTo(createdBoardTo.getId());
    }

    @Test
    void updateListNameTest() {
        String createdBoardName = "board for updateListNameTest";
        String oldListName = "list for updateListNameTest";
        String newListName = "new list name for updateListNameTest";

        BoardDto createdBoard = new RestBoardService()
            .createBoard(createdBoardName);

        ListDto createdList = new RestListService()
            .createList(createdBoard.getId(), oldListName);

        new RestListService()
            .updateListName(createdList.getId(), newListName);

        new RestListAssertions(createdList)
            .verifyNewListNameById(newListName, oldListName);
    }
}
