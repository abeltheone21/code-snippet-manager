package model;

public class Snippet {
    private int id;
    private String title;
    private String language;
    private String code;
    private String description;
    private boolean favorite;
    public Snippet(int id, String title, String language, String code, String description,boolean favorite) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;
        this.favorite=favorite;

    }
    public Snippet(String title, String language, String code, String description,boolean favorite) {
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;
        this.favorite=favorite;


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
    public boolean isFavorite(){
        return favorite;
    }
    //setter methodes
    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setLanguage(String language){
        this.language=language;
    }
    public void setCode(String code){
        this.code=code;
    }
    public void setDescription(String description){
        this.description = description;
    }


}
