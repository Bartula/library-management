package recruitmenttask.librarymanagement.api;


import recruitmenttask.librarymanagement.domain.Book;

public interface BookAdmin {
    void createNewBook(Book book);

    Book updateBook(Book book);
}
