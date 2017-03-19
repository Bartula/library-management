package recruitmenttask.librarymanagement.api;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recruitmenttask.librarymanagement.domain.*;

import java.util.List;
import java.util.stream.Collectors;

import static recruitmenttask.librarymanagement.domain.BookStatus.AVAILABLE;
import static recruitmenttask.librarymanagement.domain.BookStatus.LENT;

@Service
public class BookPanel {

    private BookRepository bookRepository;
    private BookCatalog bookCatalog;
    private CustomerRepository customerRepository;

    public BookPanel(BookRepository bookRepository, BookCatalog bookCatalog, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.bookCatalog = bookCatalog;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void createNewBook(BookDto bookDto) {
        bookDto.validation();
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getYear());
        bookRepository.save(book);
    }

    @Transactional
    public void removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book != null && book.getStatus().equals(AVAILABLE))
            bookRepository.remove(book);
        else throw new InvalidRequestException("Can not remove book from library");
    }

    @Transactional
    public List<BookDto> getBooksList() {
        List<Book> books = bookCatalog.listAllBooks();
        if (books != null)
            books.stream().map(this::bookToPrint).forEach(System.out::println);
        else throw new InvalidRequestException("There are not books in library");
        return books.stream().map(book -> new BookDto(book.getTitle(), book.getAuthor(), book.getYear())).collect(Collectors.toList());
    }

    @Transactional
    public void lendBook(Long customerId, Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book.getStatus().equals(LENT))
            throw new InvalidRequestException("Book has already lent");
        Customer customer = customerRepository.findById(customerId);
        List<Book> books = customer.getBook();
        if (books != null)
            books.add(book);
        else {
            books = Lists.newLinkedList();
            books.add(book);
        }
        customer.setBook(books);
        book.setStatus(LENT);
        book.setCustomer(customer);
        bookCatalog.updateBook(book);
    }

    public List<BookDto> searchBooks(BookSearchCriteria criteria) {
        return bookCatalog.searchBooks(criteria);
    }

    private String bookToPrint(Book book) {
        String str = "Id: " + book.getId() + ", Title: " +
                book.getTitle() + ", Author: " + book.getAuthor() + ", Year: " + book.getYear();
        return book.getStatus().equals(LENT) ? str + ", Lent by: " + book.getCustomer().getName() : str + " " + book.getStatus();
        //return str;
    }
}

