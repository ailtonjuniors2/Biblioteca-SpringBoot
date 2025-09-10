package dto;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private long expiraEm;
}

