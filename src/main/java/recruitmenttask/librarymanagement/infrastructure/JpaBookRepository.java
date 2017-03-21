package recruitmenttask.librarymanagement.infrastructure;


import org.springframework.stereotype.Repository;
import recruitmenttask.librarymanagement.domain.Book;
import recruitmenttask.librarymanagement.domain.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findById(Long id) {
        List<Book> books = entityManager.createQuery("FROM Book b WHERE b.id =:id", Book.class)
                .setParameter("id", id).getResultList();
        return Utils.returnSingleResult(books);
    }

    @Override
    public void remove(Book book) {
        entityManager.remove(book);
    }

}
