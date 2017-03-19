package recruitmenttask.librarymanagement.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recruitmenttask.librarymanagement.domain.Customer;
import recruitmenttask.librarymanagement.domain.CustomerRepository;

@Service
public class CustomerPanel {

    private CustomerRepository customerRepository;

    public CustomerPanel(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void createNewCustomer(CustomerDto customerDto) {
        customerDto.validation();
        Customer customer = customerRepository.load(customerDto.getName());
        if (customer == null) {
            customer = new Customer(customerDto.getName());
            customerRepository.save(customer);
        }else
            throw new InvalidRequestException("Customer already exist");
    }


}
