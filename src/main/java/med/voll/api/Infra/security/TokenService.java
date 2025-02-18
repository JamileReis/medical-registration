package med.voll.api.Infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.Domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String gerarToken(Usuario usuario) {

    try {
      var algoritimo = Algorithm.HMAC256(secret);
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

  public String getSubject(String tokenJWT) {
    try {
      if (secret == null || secret.isEmpty()) {
        throw new IllegalStateException("Chave secreta do JWT não está configurada corretamente!");
      }

      Algorithm algoritmo = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algoritmo)
              .withIssuer("API Voll Med")
              .build();

      return verifier.verify(tokenJWT).getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Token JWT inválido ou expirado!", exception);
    }
  }

  private Instant dataExpiracao() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}

