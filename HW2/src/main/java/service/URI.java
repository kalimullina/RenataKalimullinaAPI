package service;

class URI {
    static final String GET_BY_BOARD_ID_URI = "1/boards/%s";
    static final String GET_BY_LIST_ID_URI = "1/lists/%s";
    static final String POST_BY_BOARD_NAME_URI = "1/boards";
    static final String POST_BY_LIST_NAME_AND_BOARD_ID_URI = "1/lists";
    static final String DELETE_BY_BOARD_ID_URI = "1/boards/%s";
    static final String PUT_TO_ANOTHER_BOARD_BY_LIST_ID_URI = "1/lists/%s/idBoard";
    static final String PUT_NEW_NAME_BY_LIST_ID_URI = "1/lists/%s";
    static final String GET_ALL_BOARDS_URI = "1/members/me/boards";
}
