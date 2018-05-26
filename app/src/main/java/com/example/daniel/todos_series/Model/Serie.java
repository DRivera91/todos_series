package com.example.daniel.todos_series.Model;

public class Serie {
    private String Name;
    private String Image;

    public Serie() {
    }

    public Serie(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

