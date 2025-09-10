package security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component //componente spring
public class JwtTokenProvider {
    private final String SECRET = "secret"; //mudar para variavel de ambiente no final
    private final long EXPIRACAO = 86400000; //24 horas em milissegundos

    public String gerarToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal(); //pega detalhes do usuario
        //data atual e da expiracao do token
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + EXPIRACAO);

        //constroi token usando jjwt
        return Jwts.builder()
                .setSubject(user.getUsername()) // email
                .setIssuedAt(agora) //data de criacao
                .setExpiration(validade) //data expiracao
                .signWith(SignatureAlgorithm.HS512, SECRET) //verifica integridade e autenticidade do token
                .compact(); //gera string final
    }

    //extrai email do token
    public String getEmaildoToken(String token)  {
        return Jwts.parser()
                .setSigningKey(SECRET) //verifica a partir da mesma chave
                .parseClaimsJws(token) //parse do token
                .getBody() //corpo do token
                .getSubject(); //pega o email
    }

    //valida se token e valido
    public boolean validarToken(String token) {
        try {
            //parseia o codigo se nao for valido lanca excecao
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getExpiracao() {
        return EXPIRACAO;
    }
}
