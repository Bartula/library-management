package recruitmenttask.librarymanagement.api;


import recruitmenttask.librarymanagement.domain.BookStatus;
import recruitmenttask.librarymanagement.domain.Customer;

public class BookDao {

    private String title;
    private String author;
    private String year;
    private BookStatus status;
    private Customer customer;

    public BookDao(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public BookDao(String title, String author, String year, BookStatus status, Customer customer) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
        this.customer = customer;
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

    public BookStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
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

        BookDao bookDao = (BookDao) o;

        if (title != null ? !title.equals(bookDao.title) : bookDao.title != null) return false;
        if (author != null ? !author.equals(bookDao.author) : bookDao.author != null) return false;
        if (year != null ? !year.equals(bookDao.year) : bookDao.year != null) return false;
        if (status != bookDao.status) return false;
        return customer != null ? customer.equals(bookDao.customer) : bookDao.customer == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }
}
