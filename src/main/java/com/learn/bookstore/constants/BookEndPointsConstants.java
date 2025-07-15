package com.learn.bookstore.constants;

public class BookEndPointsConstants {

    public static final String BASE = "/book";

    // Author end-points config
    public static final String CREATE_AUTHOR = BASE + "/author";
    public static final String GET_AUTHOR_BY_ID =  BASE + "/author/get/{id}";
    public static final String GET_AUTHORS_BY_NAME = BASE + "/author/get-by-name/{name}";
    public static final String GET_ALL_AUTHORS = BASE + "/author/get/all";
    public static final String UPDATE_AUTHOR_BY_ID = BASE + "/author/{id}";
    public static final String DELETE_AUTHOR_BY_ID = BASE + "/author/{id}";

    // Category end-points config
    public static final String CREATE_CATEGORY = BASE + "/category";
    public static final String GET_CATEGORY_BY_ID = BASE + "/category/get/{id}";
    public static final String GET_ALL_CATEGORIES = BASE + "/category/get/all";
    public static final String UPDATE_CATEGORY_BY_ID = BASE + "/category/{id}";
    public static final String DELETE_CATEGORY_BY_ID = BASE + "/category/{id}";

    // Book end-points config
    public static final String CREATE_NEW_BOOK = BASE + "/create-new";
    public static final String UPLOAD_COVER_BOOK_IMAGE = BASE + "/upload/cover/image/{bookId}";
    public static final String UPLOAD_BOOK_FILE = BASE + "/upload/file/{bookId}";
    public static final String GET_BOOK_BY_ID = BASE + "/get/{id}";
    public static final String GET_ALL_BOOKS = BASE + "/get/all";
    public static final String GET_BOOK_BY_CATEGORY_ID =  BASE + "/get/category/{categoryId}";
    public static final String GET_BOOK_BY_AUTHOR_ID = BASE + "/get/author/{authorId}";
    public static final String UPDATE_BOOK_BY_ID = BASE + "/update/{id}";
    public static final String DELETE_BOOK_BY_ID = BASE + "/delete/{id}";


}
