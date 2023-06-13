package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created By dhaval on 2023-06-13
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}
