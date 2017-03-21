package recruitmenttask.librarymanagement.api;


import recruitmenttask.librarymanagement.domain.Book;
import recruitmenttask.librarymanagement.domain.Customer;

public interface BookLender {
    void lendBook(Book book, Customer customer);
}
