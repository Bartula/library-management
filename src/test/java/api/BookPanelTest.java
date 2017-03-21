package api;

import org.junit.Before;
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

    @Before
    public void setUp(){
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
        customerPanel.createNewCustomer(new CustomerDto("Tuco Ramirez"));
    }

    @Test
    @Transactional
    public void shouldAddBookToLibrary() {
        //given:
        BookDto bookDto = new BookDto("Lotr", "JRRTolkien", "2017");

        //when:
        bookPanel.createNewBook(bookDto);

        //then:
        Book book = bookRepository.findById(12L);
        assertEquals(book.getTitle(), bookDto.getTitle());
    }


    @Test
    @Transactional
    public void shouldRemoveBook() {
        //given

        //when
        bookPanel.removeBook(3L);

        //then
        Book book = bookRepository.findById(3L);
        assertNull(book);
    }

    @Test
    @Transactional
    public void shouldGetBookList() {
        //given

        //when
        List<BookDao> books = bookPanel.getBooksList();

        //then
        assertEquals(books.get(0).getTitle(), "Lotr");

    }

    @Test
    @Transactional
    public void shouldLendBook(){
        //given
        customerPanel.createNewCustomer(new CustomerDto("Thomas Anderson"));

        //when
        Book book1 = bookRepository.findById(1L);
        Book book2 = bookRepository.findById(2L);
        bookPanel.lendBook(11L, book1.getId());
        bookPanel.lendBook(11L, book2.getId());

        //then
        assertEquals(book1.getStatus(), LENT);
        assertEquals(book2.getStatus(), LENT);
    }

    @Test
    @Transactional
    public void shouldSearchBooks(){
        //given
        BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
        bookSearchCriteria.setTitle("Lotr");
        bookSearchCriteria.setYear("2017");


        //when
        List<BookDao> books = bookPanel.searchBooks(bookSearchCriteria);

        //then
        assertEquals(books.size(),3);
        books.stream().map(book -> book.getAuthor()+" "+ book.getTitle()+" "+book.getYear() ).forEach(System.out::println);
    }
}
