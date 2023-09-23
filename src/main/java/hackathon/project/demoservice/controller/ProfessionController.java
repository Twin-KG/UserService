package hackathon.project.demoservice.controller;

import hackathon.project.demoservice.domain.ZResponse;
import hackathon.project.demoservice.dto.PasswordResetDto;
import hackathon.project.demoservice.dto.ProfessionDto;
import hackathon.project.demoservice.exception.domain.DataNotFoundException;
import hackathon.project.demoservice.exception.domain.UserNotFoundException;
import hackathon.project.demoservice.model.Category;
import hackathon.project.demoservice.model.Professions;
import hackathon.project.demoservice.service.CategoryService;
import hackathon.project.demoservice.service.ProfessionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/professions")
@CrossOrigin
@AllArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ZResponse<Professions>> getProfessionsByUsernameOrEmail(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email){

        Professions professions = professionService.findProfessionsByIdOrUsernameOrEmail(id, username, email)
                .orElseThrow(() -> new UserNotFoundException("Profession is not found..."));

        return ResponseEntity.ok( ZResponse.<Professions>builder()
                  .success(true)
                  .message("Gotcha")
                  .data(professions)
                  .build());

    }

    @PostMapping
    public ResponseEntity<ZResponse<Professions>> saveUser(@RequestBody ProfessionDto professionDto){


        final ModelMapper mapper = new ModelMapper();
        Professions newProfessions = mapper.map(professionDto, Professions.class);
        Optional<Category> category = categoryService.getById(professionDto.getCategoryId());
        Category data = category.orElseThrow(() -> new DataNotFoundException("Category is not found"));
        newProfessions.setCategory(data);

        newProfessions = professionService.saveUser(newProfessions);
        return ResponseEntity.ok( ZResponse.<Professions>builder()
                .success(true)
                .message("Successfully saved user")
                .data(newProfessions)
                .build());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ZResponse<Professions>> resetPasswordByEmail(
            @RequestParam String email, @RequestBody PasswordResetDto dto){

        Professions professions = professionService.resetPasswordByEmail(email, dto.getOldPassword(), dto.getNewPassword());

        return ResponseEntity.ok( ZResponse.<Professions>builder()
                .success(true)
                .message("Successfully reset your password.")
                .data(professions)
                .build());
    }

}
