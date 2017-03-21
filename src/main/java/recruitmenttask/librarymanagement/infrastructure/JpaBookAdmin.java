package recruitmenttask.librarymanagement.infrastructure;

import org.springframework.stereotype.Repository;
import recruitmenttask.librarymanagement.api.BookAdmin;
import recruitmenttask.librarymanagement.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaBookAdmin implements BookAdmin {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createNewBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book updateBook(Book book) {
        return entityManager.merge(book);
    }

}
