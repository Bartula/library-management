package recruitmenttask.librarymanagement.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import recruitmenttask.librarymanagement.api.*;
import recruitmenttask.librarymanagement.domain.BookRepository;
import recruitmenttask.librarymanagement.domain.CustomerRepository;
import recruitmenttask.librarymanagement.infrastructure.JpaBookRepository;
import recruitmenttask.librarymanagement.infrastructure.JpaCustomerRepository;

import java.util.List;

import static recruitmenttask.librarymanagement.domain.BookStatus.LENT;


public class ConsoleApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        BookPanel bookPanel = applicationContext.getBean("bookPanel", BookPanel.class);
        CustomerPanel customerPanel = applicationContext.getBean("customerPanel", CustomerPanel.class);

        addCustomers(customerPanel);
        addBooksToLibrary(bookPanel);

        //Books list ==============================================================
        List<BookDao> books = bookPanel.getBooksList();
        booksDaoPrint(books);

        //Lend books ==============================================================

        bookPanel.lendBook(1L, 6L);
        bookPanel.lendBook(1L, 10L);
        bookPanel.lendBook(2L, 13L);

        booksDaoPrint(bookPanel.getBooksList());

        //Search books ==============================================================
        BookSearchCriteria criteria = new BookSearchCriteria();
        criteria.setAuthor("Mario Puzo");
        criteria.setYear("2014");

        booksDaoPrint(bookPanel.searchBooks(criteria));

        //Remove Books ==============================================================
        bookPanel.removeBook(4L);
        bookPanel.removeBook(5L);
        bookPanel.removeBook(9L);
        bookPanel.removeBook(11L);

        booksDaoPrint(bookPanel.getBooksList());

    }


    private static void addBooksToLibrary(BookPanel bookPanel) {
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2010"));
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2010"));
        bookPanel.createNewBook(new BookDto("Lotr", "JRRTolkien", "2017"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2015"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2016"));
        bookPanel.createNewBook(new BookDto("Witcher", "Sapkowski", "2016"));
        bookPanel.createNewBook(new BookDto("Earthsea", "Ursula K. Le Guin", "2017"));
        bookPanel.createNewBook(new BookDto("Earthsea", "Ursula K. Le Guin", "2011"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2011"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2014"));
        bookPanel.createNewBook(new BookDto("The Godfather", "Mario Puzo", "2014"));
        bookPanel.createNewBook(new BookDto("Wizard's First Rule", "Terry Goodkind", "2011"));
        bookPanel.createNewBook(new BookDto("Wizard's First Rule", "Terry Goodkind", "2014"));
    }

    private static void addCustomers(CustomerPanel customerPanel) {
        customerPanel.createNewCustomer(new CustomerDto("Tuco Ramirez"));
        customerPanel.createNewCustomer(new CustomerDto("Thomas Anderson"));
    }

    private static void booksDaoPrint(List<BookDao> books) {
        final String[] str = new String[1];
        books.stream().map(bookDao -> {
            str[0] = "Title: " + bookDao.getTitle() + ", Author: " + bookDao.getAuthor() + ", Year: " + bookDao.getYear();
            if (bookDao.getStatus() == null && bookDao.getCustomer() == null)
                return  str[0];
            return bookDao.getStatus().equals(LENT) ? str[0] + ", Lent by: " + bookDao.getCustomer().getName() : str[0] + " " + bookDao.getStatus();
        }).forEach(System.out::println);
    }
}
