package com.example.daniel.todos_series.Model;

public class Chapter {
    private String Name;
    private String Image;
    private String Description;
    private String SeasonId;

    public Chapter() {
    }

    public Chapter(String name, String image) {
        Name = name;
        Image = image;
    }

    public Chapter(String name, String image, String description, String seasonId) {
        Name = name;
        Image = image;
        Description = description;
        SeasonId = seasonId;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSeasonId() {
        return SeasonId;
    }

    public void setSeasonId(String seasonId) {
        SeasonId = seasonId;
    }
}
