package tacos.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.entities.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByZip(String zip);

    @Query("SELECT Order from Order o WHERE o.zip = ?1 and o.placedAt between ?2 and ?3")
    List<Order> findAllByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);

    // select * from orders where zip = ? and placedAt between ? and ?
    // jpql
}
