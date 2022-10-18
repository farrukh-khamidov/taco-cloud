package tacos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tacos.entities.Order;

import java.util.Date;
import java.util.List;
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByZip(String zip);
    List<Order> findAllByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);

    // select * from orders where zip = ? and placedAt between ? and ?
    // jpql
}
