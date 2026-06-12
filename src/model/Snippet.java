package model;

public class Snippet {
    private int id;
    private String title;
    private String language;
    private String code;
    private String description;
    public Snippet(int id, String title, String language, String code, String description) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;

    }
    public Snippet(String title, String language, String code, String description) {
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;

    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getLanguage(){
        return language;
    }
    public String getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

}
