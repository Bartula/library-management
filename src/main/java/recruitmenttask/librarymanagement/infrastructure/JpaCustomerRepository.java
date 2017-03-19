package recruitmenttask.librarymanagement.infrastructure;


import org.springframework.stereotype.Repository;
import recruitmenttask.librarymanagement.domain.Customer;
import recruitmenttask.librarymanagement.domain.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaCustomerRepository implements CustomerRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer load(String name) {
        List<Customer> customers = entityManager.createQuery("FROM Customer c WHERE c.name =:name", Customer.class)
                .setParameter("name", name).getResultList();
        return Utils.returnSingleResult(customers);
    }

    @Override
    public Customer findById(Long id) {
        List<Customer> customer = entityManager.createQuery("FROM Customer c WHERE c.id =:id", Customer.class)
                .setParameter("id", id).getResultList();
        return Utils.returnSingleResult(customer);
    }
}
