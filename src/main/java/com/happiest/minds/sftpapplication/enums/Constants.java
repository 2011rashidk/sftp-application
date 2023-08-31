package com.happiest.minds.sftpapplication.enums;

public enum Constants {

    LIST_OF_FILES_RETRIEVED("list of files retrieved successfully"),
    FILE_EMPTY("File is null or empty"),
    EMPLOYEES("/employees"),
    ID_PLACE_HOLDER("/{id}"),
    NO_DATA_FOUND("Not found data with id: "),
    RECORD_DELETED_FOR_ID("Record deleted for id: "),
    ADMIN("admin"),
    EXCEPTION("Exception: {}"),
    FILES_CONTENT_RETRIEVED("Files contents retrieved successfully"),
    FILES_CONTENT_SAVED("Files contents saved to database successfully");


    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
