package com.project.semicolon.mysupplements.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Category {
    private String name;
    private String image;
    private String translate;
    private int id;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
