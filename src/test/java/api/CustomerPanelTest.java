package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import recruitmenttask.librarymanagement.api.CustomerDto;
import recruitmenttask.librarymanagement.api.CustomerPanel;
import recruitmenttask.librarymanagement.domain.Customer;
import recruitmenttask.librarymanagement.domain.CustomerRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration("/application.xml")
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
public class CustomerPanelTest {

    @Autowired
    private CustomerPanel customerPanel;
    @Autowired
    private CustomerRepository customerRepository;


    @Test
    @Transactional
    public void shouldAddCustomer() {
        //given
        CustomerDto customerDto = new CustomerDto("Tuco Ramirez");

        //when
        customerPanel.createNewCustomer(customerDto);

        //then
        Customer customer = customerRepository.findById(1L);
        assertEquals(customer.getName(), "Tuco Ramirez");
        assertNull(customer.getBook());
    }

}
