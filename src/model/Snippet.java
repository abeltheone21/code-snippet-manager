package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Snippet {
    private int id;
    private String title;
    private String language;
    private String code;
    private String description;
    private boolean favorite;
    private LocalDateTime createdat;
    private LocalDateTime updatetime;

    // Constructor with id (for reading from database)
    public Snippet(int id, String title, String language, String code, String description,
                   boolean favorite, LocalDateTime createdat, LocalDateTime updatetime) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;
        this.favorite = favorite;
        this.createdat = createdat;
        this.updatetime = updatetime;
    }

    // Constructor without id (for new snippets)
    public Snippet(String title, String language, String code, String description, boolean favorite) {
        this.title = title;
        this.language = language;
        this.code = code;
        this.description = description;
        this.favorite = favorite;
        this.createdat = LocalDateTime.now();
        this.updatetime = LocalDateTime.now();
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public boolean isFavorite() { return favorite; }
    public LocalDateTime getCreatedat() { return createdat; }
    public LocalDateTime getUpdatetime() { return updatetime; }

    // Formatted date getters
    public String getFormattedCreatedAt() {
        if (createdat == null) return "Unknown";
        return createdat.format(DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a"));
    }

    public String getFormattedUpdatedAt() {
        if (updatetime == null) return "Unknown";
        return updatetime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a"));
    }

    // Setters
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setLanguage(String language) { this.language = language; }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatedat(LocalDateTime createdat) { this.createdat = createdat; }
    public void setUpdatetime(LocalDateTime updatetime) { this.updatetime = updatetime; }
}