package hackathon.project.demoservice.controller;

import hackathon.project.demoservice.client.ContentServiceClient;
import hackathon.project.demoservice.domain.ZResponse;
import hackathon.project.demoservice.dto.PasswordResetDto;
import hackathon.project.demoservice.exception.domain.UserNotFoundException;
import hackathon.project.demoservice.model.Professions;
import hackathon.project.demoservice.service.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professions")
@AllArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;

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
    public ResponseEntity<ZResponse<Professions>> saveUser(@RequestBody Professions newProfessions){
        Professions professions = professionService.saveUser(newProfessions);
        return ResponseEntity.ok( ZResponse.<Professions>builder()
                .success(true)
                .message("Successfully saved user")
                .data(professions)
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
