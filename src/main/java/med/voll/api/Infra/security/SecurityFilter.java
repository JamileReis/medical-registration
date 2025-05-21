package med.voll.api.Infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try {

           var tokenJWT = recuperarToken(request);

           if (tokenJWT != null) {
               var subject = tokenService.getSubject(tokenJWT);
               System.out.println(subject);
               var usuario = repository.findByLogin(subject);
               System.out.println(usuario.getAuthorities());
               var authentication = new UsernamePasswordAuthenticationToken(usuario, tokenJWT, usuario.getAuthorities());
               System.out.println(authentication);
               SecurityContextHolder.getContext().setAuthentication(authentication);


               SecurityContextHolder.getContext().setAuthentication(authentication);



           }

           filterChain.doFilter(request, response);
       } catch (Exception e){
           System.out.println(e.getMessage());

       }
    }

    private String recuperarToken(HttpServletRequest request) {
        {
            var authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                return authorizationHeader.substring(7); // Remove "Bearer " e retorna apenas o token
            }
            return null;
        }
    }
}