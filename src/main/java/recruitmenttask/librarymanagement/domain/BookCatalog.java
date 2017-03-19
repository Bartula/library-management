package recruitmenttask.librarymanagement.domain;


import recruitmenttask.librarymanagement.api.BookDto;
import recruitmenttask.librarymanagement.api.BookSearchCriteria;

import java.util.List;

public interface BookCatalog {
    List<Book> listAllBooks();

    Book updateBook(Book book);

    List<BookDto> searchBooks(BookSearchCriteria criteria);
}
