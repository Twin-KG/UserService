package hackathon.project.demoservice.controller;

import hackathon.project.demoservice.domain.ZResponse;
import hackathon.project.demoservice.exception.domain.DataNotFoundException;
import hackathon.project.demoservice.model.Category;
import hackathon.project.demoservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professions/categories")
@AllArgsConstructor
@CrossOrigin
public class ProfessionCatgController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ZResponse<List<Category>>> getAllCategoryList(){
        List<Category> categoryList = categoryService.getAll();
        return ResponseEntity.ok(ZResponse.<List<Category>>builder()
                .message("Gotcha")
                .code(HttpStatus.OK.value())
                .success(true)
                .data(categoryList)
                .build());
    }

    @GetMapping("/id")
    public ResponseEntity<ZResponse<Category>> getCategoryById(@RequestParam Long id){
        Optional<Category> categoryOptional = categoryService.getById(id);

        Category category = categoryOptional.orElseThrow(() -> new DataNotFoundException("Category is not found"));

        return ResponseEntity.ok(ZResponse.<Category>builder()
                .message("Gotcha")
                .code(HttpStatus.OK.value())
                .success(true)
                .data(category)
                .build());
    }

}
