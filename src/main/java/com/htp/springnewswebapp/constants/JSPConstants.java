package com.htp.springnewswebapp.constants;

public final class JSPConstants {

    public static final String JSP_NEWS = "news";
    public static final String AUTH_ERROR = "auth_error";
    private static final String LOGIN = "login";
    public static final String JSP_LOGIN_PARAM = "login";
    public static final String JSP_PASSWORD_PARAM = "password";
    public final static String JSP_COUNT_PARAM = "count";
    public final static String JSP_PAGE_NUMBER_PARAM = "page";
    public final static String JSP_FINAL_PAGE_NUMBER = "final_page_number";
    public final static int DEFAULT_PAGE = 1;
    public final static String DEFAULT_PAGE_STRING = "1";
    public final static String DEFAULT_COUNT_STRING = "5";

    private JSPConstants(){}


    public static final String LOCALE = "local";
    public static final String LAST_REQUEST_NAME = "last_request_name";
    public static final String LAST_REQUEST_PARAMS = "last_request_params";
    public static final String CURRENT_REQUEST_NAME = "current_request_name";
    public static final String CURRENT_REQUEST_PARAMS = "current_request_params";

    public static final String JSP_SAVE_SUCCESS = "save_success";
    public static final String JSP_DELETE_SUCCESS = "delete_success";

    public static final String ERR = "err";
    public static final String SUC = "suc";


    public static final String ERROR_MESSAGE = "error_msg";



    public static final String COMMAND = "command";
    public static final String NEWS = "news";


    public static final String PRESENTATION = "presentation";

    // PRESENTATION TYPES
    public static final String VIEW_NEWS = "viewNews";
    public static final String EDIT_NEWS = "editNews";
    public static final String ADD_NEWS = "addNews";
    public static final String REGISTRATION = "registration";
    public static final String NEWS_LIST = "newsList";

    // CONTROLLER COMMANDS
    public static final String CONTROLLER_GO_TO_NEWS_LIST = "controller?command=go_to_news_list";
    public static final String CONTROLLER_GO_TO_ERROR_PAGE = "controller?command=go_to_error_page";
    public static final String CONTROLLER_GO_TO_BASE_PAGE = "controller?command=go_to_base_page";

    // FORWARD URIs

    public static final String  BASE_LAYOUT_JSP_URI = "WEB-INF/pages/layouts/baseLayout.jsp";


}
