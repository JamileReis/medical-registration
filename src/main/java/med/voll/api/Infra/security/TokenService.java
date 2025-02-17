package med.voll.api.Infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.Domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
  public String gerarToken(Usuario usuario) {

    try {
      var algoritimo = Algorithm.HMAC256("123456789");
      return JWT.create()
              .withIssuer("API Voll Med")
              .withSubject(usuario.getLogin())
              .withClaim("id", usuario.getId())
              .withExpiresAt(dataExpiracao())
              .sign(algoritimo);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token JWT", exception);

    }
  }

  private Instant dataExpiracao() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}

