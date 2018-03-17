package vn.edu.tdc.nhom2.nhatky.Models;

/**
 * Created by TIEN on 3/17/2018.
 */

public class Post {
    private int ID;
    private String Title;
    private String Content;

    public Post(int ID, String title, String content) {
        this.ID = ID;
        Title = title;
        Content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
