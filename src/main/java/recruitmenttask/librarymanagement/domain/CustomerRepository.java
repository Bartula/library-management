package recruitmenttask.librarymanagement.domain;


public interface CustomerRepository {
    void save(Customer customer);

    Customer load(String name);

    Customer findById(Long id);
}
