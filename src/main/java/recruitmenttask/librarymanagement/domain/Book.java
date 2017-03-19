package recruitmenttask.librarymanagement.domain;

import javax.persistence.*;

import static recruitmenttask.librarymanagement.domain.BookStatus.*;


@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String year;
    private BookStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;


    public Book() {
    }

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = AVAILABLE;
    }

    public Customer getCustomer() {
        return customer;
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

    public BookStatus getStatus() throws NullPointerException{
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
