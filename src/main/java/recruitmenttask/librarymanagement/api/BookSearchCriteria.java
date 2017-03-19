package recruitmenttask.librarymanagement.api;


public class BookSearchCriteria {

    private String title;
    private String author;
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isTitleDefined() {
        return title != null;
    }

    public boolean isAuthorDefined() {
        return author != null;
    }

    public boolean isYearDefined() {
        return year != null;
    }
}
