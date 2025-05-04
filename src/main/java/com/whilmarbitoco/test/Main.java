package com.whilmarbitoco.test;

import com.whilmarbitoco.inkspace.repository.BookRepository;
import com.whilmarbitoco.inkspace.utils.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {



    public static void main(String[] args) {
        BookRepository br = new BookRepository();

        br.deleteWhere("BookID = ? AND name = ? OR a = ?", 1, "john", "20");
    }
}
