package com.example.project.Domain;

public class CategoryDomain {
    private String title;
    private String pic;
    private String id;

    public CategoryDomain(String title, String pic, String id) {
        this.title = title;
        this.pic = pic;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }
}
