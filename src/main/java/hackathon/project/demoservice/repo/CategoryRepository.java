package hackathon.project.demoservice.repo;

import hackathon.project.demoservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
