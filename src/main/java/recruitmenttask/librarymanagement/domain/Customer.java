package recruitmenttask.librarymanagement.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> book;


    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
        //this.book = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
