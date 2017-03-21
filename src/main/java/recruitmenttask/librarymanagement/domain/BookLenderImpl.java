package recruitmenttask.librarymanagement.domain;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import recruitmenttask.librarymanagement.api.BookLender;

import java.util.List;

import static recruitmenttask.librarymanagement.domain.BookStatus.LENT;

/**
 * Created by Bartosz on 2017-03-20.
 */
@Component
public class BookLenderImpl implements BookLender {
    @Override
    public void lendBook(Book book, Customer customer) {
        if (book.getStatus().equals(LENT)) {
            throw new IllegalArgumentException("Book has already lent");
        }
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
    }
}
