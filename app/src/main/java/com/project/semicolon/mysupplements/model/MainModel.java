package com.project.semicolon.mysupplements.model;

public class MainModel {
    private String main;
    private int icon;

    public MainModel(String main, int icon) {
        this.main = main;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
