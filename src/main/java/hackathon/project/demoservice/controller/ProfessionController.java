package hackathon.project.demoservice.controller;

import hackathon.project.demoservice.client.ContentServiceClient;
import hackathon.project.demoservice.domain.Tier;
import hackathon.project.demoservice.domain.ZResponse;
import hackathon.project.demoservice.dto.PasswordResetDto;
import hackathon.project.demoservice.dto.ProfessionDto;
import hackathon.project.demoservice.enumeration.Role;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professions")
@CrossOrigin
@AllArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;
    private final CategoryService categoryService;
    private final ContentServiceClient contentServiceClient;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<ZResponse<ProfessionDto>> getProfessionsByUsernameOrEmail(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long category_id){

        ProfessionDto professions = professionService.findProfessionsByIdOrUsernameOrEmailOrCategoryId(id, username, email, category_id)
                .orElseThrow(() -> new UserNotFoundException("Profession is not found..."));

        return ResponseEntity.ok( ZResponse.<ProfessionDto>builder()
                  .success(true)
                  .message("Gotcha")
                  .data(professions)
                  .build());

    }

    @GetMapping("/search")
    public ResponseEntity<ZResponse<List<Professions>>> getProfessionsByUsername(
            @RequestParam String username){

        List<Professions> professions = professionService.getProfessionsByUsernameLike(username);

        return ResponseEntity.ok( ZResponse.<List<Professions>>builder()
                .success(true)
                .message("Gotcha")
                .data(professions)
                .build());

    }

    @GetMapping("/mail")
    public ResponseEntity<ZResponse<ProfessionDto>> getProfessionsByMail(
            @RequestParam String email){

        ModelMapper mapper = new ModelMapper();

        ProfessionDto response = null;

        Optional<Professions> professionsOptional = professionService.findProfessionsByMail(email);
        Professions professions = professionsOptional.orElseThrow(() -> new UserNotFoundException("User is not found"));
        response = mapper.map(professions, ProfessionDto.class);

        ZResponse<List<Tier>> tierListResponse = contentServiceClient.findTiersByProfessionId(professions.getId());
        if(tierListResponse != null){
            response.setTierList(tierListResponse.getData());
        }

        return ResponseEntity.ok( ZResponse.<ProfessionDto>builder()
                .success(true)
                .message("Gotcha")
                .data(response)
                .build());

    }

    @PostMapping
    public ResponseEntity<ZResponse<ProfessionDto>> saveUser(@RequestBody ProfessionDto professionDto){

        ProfessionDto result= saveOrUpdateProfession(professionDto);

        return ResponseEntity.ok( ZResponse.<ProfessionDto>builder()
                .success(true)
                .message("Successfully saved user")
                .data(result)
                .build());
    }

    @PutMapping
    public ResponseEntity<ZResponse<ProfessionDto>> updateUser(@RequestBody ProfessionDto professionDto){

        ProfessionDto result= saveOrUpdateProfession(professionDto);

        return ResponseEntity.ok( ZResponse.<ProfessionDto>builder()
                .success(true)
                .message("Successfully updated user")
                .data(result)
                .build());
    }

    public ProfessionDto saveOrUpdateProfession(ProfessionDto professionDto){
        ProfessionDto result = null;

        Professions newProfessions = mapper.map(professionDto, Professions.class);

        if(professionDto.getCategoryId() != null){
            Optional<Category> category = categoryService.getById(professionDto.getCategoryId());
            Category data = category.orElseThrow(() -> new DataNotFoundException("Category is not found"));
            newProfessions.setCategory(data);
        }

        newProfessions.setId(null);
        return professionService.saveUser(newProfessions);
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
