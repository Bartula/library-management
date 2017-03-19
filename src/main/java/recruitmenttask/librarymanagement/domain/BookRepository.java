package recruitmenttask.librarymanagement.domain;


public interface BookRepository {
    void save(Book book);

    Book findById(Long id);

    void remove(Book book);
}
