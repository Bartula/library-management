package recruitmenttask.librarymanagement.domain;

import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository {
    void save(Customer customer);

    Customer load(String name);

    Customer findById(Long id);
}
