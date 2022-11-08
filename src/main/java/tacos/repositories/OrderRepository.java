package tacos.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tacos.entities.Order;
import tacos.entities.User;

import java.util.Date;
import java.util.List;
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByZip(String zip);
    List<Order> findAllByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);

    List<Order> findByUserOrderByPlacedAtDesc(User user);
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    // select * from orders where zip = ? and placedAt between ? and ?
    // jpql
}
