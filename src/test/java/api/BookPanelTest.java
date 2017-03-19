package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import recruitmenttask.librarymanagement.api.*;
import recruitmenttask.librarymanagement.domain.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static recruitmenttask.librarymanagement.domain.BookStatus.LENT;


@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
public class BookPanelTest {

    @Autowired
    private BookPanel bookPanel;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CustomerPanel customerPanel;

    @Test
    @Transactional
    public void shouldAddBookToLibrary() {
        //given:
        BookDto bookDto = new BookDto("Lotr", "JRRTolkien", "2017");

        //when:
        bookPanel.createNewBook(bookDto);

        //then:
        Book book = bookRepository.findById(1L);
        assertEquals(book.getTitle(), bookDto.getTitle());
    }


    @Test
    @Transactional
    public void shouldRemoveBook() {
        //given
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));

        //when
        bookPanel.removeBook(1L);

        //then
        Book book = bookRepository.findById(1L);
        assertNull(book);
    }

    @Test
    @Transactional
    public void shouldGetBookList() {
        //given
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2017"));
        bookPanel.createNewBook(new BookDto("Earthsea", "Ursula K. Le Guin", "2017"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2017"));

        //when
        List<BookDto> books = bookPanel.getBooksList();

        //then
        assertEquals(books.get(0).getTitle(), "Lotr");

    }

    @Test
    @Transactional
    public void shouldLendBook(){
        //given
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2017"));
        customerPanel.createNewCustomer(new CustomerDto("Tuco Ramirez"));

        //when
        bookPanel.lendBook(3L,1L);
        bookPanel.lendBook(3L,2L);

        //then
        Book book = bookRepository.findById(1L);
        assertEquals(book.getStatus(), LENT);
        bookPanel.getBooksList();
    }

    @Test
    @Transactional
    public void shouldSearchBooks(){
        //given
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2015"));
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2015"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2017"));
        bookPanel.createNewBook(new BookDto("Earthsea", "Ursula K. Le Guin", "2016"));
        bookPanel.createNewBook(new BookDto("Earthsea", "Ursula K. Le Guin", "2017"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2016"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2017"));

        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
        bookSearchCriteria.setTitle("Lotr");
        bookSearchCriteria.setYear("2017");

        BookSearchCriteria bookSearchCriteria2 = new BookSearchCriteria();
        bookSearchCriteria2.setYear("2017");

        //when
        List<BookDto> books = bookPanel.searchBooks(bookSearchCriteria);

        //then
        books.stream().map(book -> book.getAuthor()+" "+ book.getTitle()+" "+book.getYear() ).forEach(System.out::println);
    }
}
