package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created By dhaval on 2023-06-14
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
