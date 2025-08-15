package com.learn.bookstore.constants;

public class UserEndPointsConstants {

    public static final String BASE_USER = "/user";

    // USER END-POINTS
    public static final String LOGIN = BASE_USER + "/login";
    public static final String REGISTER_USER = BASE_USER + "/register";
    public static final String REGISTER_ADMIN = BASE_USER + "/register/admin";
    public static final String GET_USER_BY_ID = BASE_USER + "/get/{id}";
    public static final String GET_CURR_USER =  BASE_USER + "/get-curr";
    public static final String GET_ALL_USERS = BASE_USER + "/get-all";
    public static final String ADD_PHONE_NUMBER =  BASE_USER + "/add-phone";
    public static final String UPDATE_USER_ADMIN =  BASE_USER + "/update";
    public static final String DELETE_USER_ADMIN =  BASE_USER + "/delete";
    public static final String DELETE_USER_BY_EMAIL = BASE_USER + "/delete/{email}";

}
