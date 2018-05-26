package com.example.daniel.todos_series.Model;

public class Season {
    private String Name;
    private String Image;
    private String MenuId;


    public Season() {
    }

    public Season(String name, String image, String menuId) {
        Name = name;
        Image = image;
        MenuId = menuId;
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

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
