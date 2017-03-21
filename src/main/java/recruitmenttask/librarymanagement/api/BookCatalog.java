package recruitmenttask.librarymanagement.api;


import java.util.List;

public interface BookCatalog {
    List<BookDao> listAllBooks();

    List<BookDao> searchBooks(BookSearchCriteria criteria);
}
