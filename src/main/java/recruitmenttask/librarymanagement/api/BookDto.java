package recruitmenttask.librarymanagement.api;


public class BookDto {

    private String title;
    private String author;
    private String year;

    public BookDto(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public void validate(){
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty");
        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Author can not be empty");
        if (year == null || year.trim().isEmpty())
            throw new IllegalArgumentException("Year can not be empty");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDto bookDto = (BookDto) o;

        if (title != null ? !title.equals(bookDto.title) : bookDto.title != null) return false;
        if (author != null ? !author.equals(bookDto.author) : bookDto.author != null) return false;
        return year != null ? year.equals(bookDto.year) : bookDto.year == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
