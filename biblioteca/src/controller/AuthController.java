package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController //controlador rest
@RequestMapping("/auth") //todas as rotas começam com /auth
public class AuthController {
    private final AuthService authService; //serviço

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
