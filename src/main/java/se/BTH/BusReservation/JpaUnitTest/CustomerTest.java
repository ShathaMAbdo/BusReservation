package se.BTH.BusReservation.JpaUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import se.BTH.BusReservation.domain.Customer;
import se.BTH.BusReservation.repositories.CustomerRepository;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // All tests are conducted on a non-genuine database
public class CustomerTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerRepository repository;

    @Test
    public void should_find_no_customers_if_repository_is_empty() {
        Iterable<Customer> customers = repository.findAll();

        assertThat(customers).isEmpty();
    }

    @Test
    public void should_store_a_customer() {
        Customer customer = repository.save(new Customer("Jack",9999,"jack@bth.se"));

        assertThat(customer).hasFieldOrPropertyWithValue("name", "Jack");
        assertThat(customer).hasFieldOrPropertyWithValue("national", 9999);
        assertThat(customer).hasFieldOrPropertyWithValue("email", "jack@bth.se");

    }

    @Test
    public void should_delete_all_customer() {
        entityManager.persist(new Customer("Jack",9999,"jack@bth.se"));
        entityManager.persist(new Customer("Adam", 1213,"adam@bth.se"));

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_all_customers() {
        Customer customer1 = new Customer("Jack",9999,"jack@bth.se");
        entityManager.persist(customer1);

        Customer customer2 = new Customer("Adam", 1213,"adam@bth.se");
        entityManager.persist(customer2);

        Customer customer3 = new Customer("Peter",1444,"peter@bth.se");
        entityManager.persist(customer3);

        Iterable<Customer> customers = repository.findAll();

        assertThat(customers).hasSize(3).contains(customer1, customer2, customer3);
    }

    @Test
    public void should_find_customer_by_id() {
        Customer customer1 = new Customer("Jack",9999,"jack@bth.se");
        entityManager.persist(customer1);

        Customer customer2 = new Customer("Adam", 1213,"adam@bth.se");
        entityManager.persist(customer2);

        Customer foundCustomer = repository.findByNational(customer2.getNational()).get(0);

        assertThat(foundCustomer).isEqualTo(customer2);
    }
}