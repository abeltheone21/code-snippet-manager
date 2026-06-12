package main;

import com.formdev.flatlaf.FlatDarkLaf;
import ui.mainframe;

public class Main {

    public static void main(String[] args) {

        FlatDarkLaf.setup();

        new mainframe();
    }
}