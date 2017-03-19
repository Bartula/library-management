package recruitmenttask.librarymanagement.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import recruitmenttask.librarymanagement.api.*;

import java.util.List;


public class ConsoleApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"application.xml"});
        BookPanel bookPanel = applicationContext.getBean("bookPanel", BookPanel.class);
        CustomerPanel customerPanel = applicationContext.getBean("customerPanel", CustomerPanel.class);

        addBooksToLibrary(bookPanel);
        addCustomers(customerPanel);

        //Books list ==============================================================
        bookPanel.getBooksList();

        //Lend books ==============================================================
        bookPanel.lendBook(14L, 1L);
        bookPanel.lendBook(14L, 4L);
        bookPanel.lendBook(15L, 7L);

        bookPanel.getBooksList();

        //Search books ==============================================================
        BookSearchCriteria criteria = new BookSearchCriteria();
        criteria.setAuthor("Mario Puzo");
        criteria.setYear("2014");

        List<BookDto> books = bookPanel.searchBooks(criteria);
        books.stream().map(book -> book.getTitle() + " " + book.getAuthor() + " " + book.getYear()).forEach(System.out::println);

        //Remove Books ==============================================================
        bookPanel.removeBook(2L);
        bookPanel.removeBook(6L);
        bookPanel.removeBook(10L);
        bookPanel.removeBook(11L);

        bookPanel.getBooksList();

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

}
