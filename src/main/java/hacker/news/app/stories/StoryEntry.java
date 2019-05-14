package hacker.news.app.stories;

public class StoryEntry {
    private long id;
    private String title;
    private String url;

    public StoryEntry(long id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public StoryEntry(String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
