package com.example.manga;

public class Model {
    String name;
    int image;
    String description;

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Model(String name, int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }
}
