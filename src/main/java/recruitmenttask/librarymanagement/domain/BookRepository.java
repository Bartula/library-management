package recruitmenttask.librarymanagement.domain;

import org.springframework.stereotype.Component;

@Component
public interface BookRepository {
    Book findById(Long id);

    void remove(Book book);
}
