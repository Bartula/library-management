package recruitmenttask.librarymanagement.infrastructure;

import org.springframework.stereotype.Repository;
import recruitmenttask.librarymanagement.api.BookDto;
import recruitmenttask.librarymanagement.api.BookSearchCriteria;
import recruitmenttask.librarymanagement.domain.Book;
import recruitmenttask.librarymanagement.domain.BookCatalog;
import recruitmenttask.librarymanagement.domain.Book_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


@Repository
public class JpaBookCatalogRepository implements BookCatalog {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> listAllBooks() {
        return entityManager.createQuery("FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book updateBook(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public List<BookDto> searchBooks(BookSearchCriteria criteria) {
        checkNotNull(criteria);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookDto> query = builder.createQuery(BookDto.class);
        Root<Book> root = query.from(Book.class);

        selectBookDto(builder, query, root);
        applyCriteria(criteria, builder, query, root);

        return entityManager.createQuery(query).getResultList();
    }

    private void selectBookDto(CriteriaBuilder builder, CriteriaQuery<BookDto> query, Root<Book> root) {
        query.select(builder.construct(BookDto.class,
                root.get(Book_.title),
                root.get(Book_.author),
                root.get(Book_.year)
        ));
    }

    private void applyCriteria(BookSearchCriteria criteria, CriteriaBuilder builder, CriteriaQuery<BookDto> query, Root<Book> root) {
        Collection<Predicate> predicates = new HashSet<>();
        ifTitleIsDefined(criteria, builder, root, predicates);
        ifAuthorIsDefined(criteria, builder, root, predicates);
        ifYearIsDefined(criteria, builder, root, predicates);
        query.where(predicates.toArray(new Predicate[]{}));
    }

    private void ifTitleIsDefined(BookSearchCriteria criteria, CriteriaBuilder builder, Root<Book> root, Collection<Predicate> predicates) {
        if (criteria.isTitleDefined())
            predicates.add(builder.equal(root.get(Book_.title), criteria.getTitle()));
    }

    private void ifAuthorIsDefined(BookSearchCriteria criteria, CriteriaBuilder builder, Root<Book> root, Collection<Predicate> predicates) {
        if (criteria.isAuthorDefined())
            predicates.add(builder.equal(root.get(Book_.author), criteria.getAuthor()));
    }

    private void ifYearIsDefined(BookSearchCriteria criteria, CriteriaBuilder builder, Root<Book> root, Collection<Predicate> predicates) {
        if (criteria.isYearDefined())
            predicates.add(builder.equal(root.get(Book_.year), criteria.getYear()));
    }

}
