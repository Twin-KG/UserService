package hackathon.project.demoservice.service;

import hackathon.project.demoservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> getById(Long id);
}
