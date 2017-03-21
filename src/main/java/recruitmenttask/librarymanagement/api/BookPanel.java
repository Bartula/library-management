package recruitmenttask.librarymanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recruitmenttask.librarymanagement.domain.*;

import java.util.List;

import static recruitmenttask.librarymanagement.domain.BookStatus.AVAILABLE;

@Service
public class BookPanel {

    private BookRepository bookRepository;
    private BookCatalog bookCatalog;
    private CustomerRepository customerRepository;
    private BookAdmin bookAdmin;
    private BookLender bookLender;

    public BookPanel(BookRepository bookRepository, BookCatalog bookCatalog, CustomerRepository customerRepository, BookAdmin bookAdmin, BookLender bookLender) {
        this.bookRepository = bookRepository;
        this.bookCatalog = bookCatalog;
        this.customerRepository = customerRepository;
        this.bookAdmin = bookAdmin;
        this.bookLender = bookLender;
    }

    @Transactional
    public void createNewBook(BookDto bookDto) {
        bookDto.validate();
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear());
        bookAdmin.createNewBook(book);
    }

    @Transactional
    public void removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book.getStatus().equals(AVAILABLE))
            bookRepository.remove(book);
        else throw new IllegalArgumentException("Can not remove book from library");
    }

    @Transactional
    public List<BookDao> getBooksList() {
        return bookCatalog.listAllBooks();
    }

    @Transactional
    public void lendBook(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId);
        Book book = bookRepository.findById(bookId);
        bookLender.lendBook(book,customer);
        bookAdmin.updateBook(book);
    }

    @Transactional
    public List<BookDao> searchBooks(BookSearchCriteria criteria) {
        return bookCatalog.searchBooks(criteria);
    }
}

