package com.qa.task.page;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Page {

    LOGIN("Login"),
    MEN("Men"),
    WOMEN("Women"),
    MY_ACCOUNT("My Account");

    private final String title;

}
