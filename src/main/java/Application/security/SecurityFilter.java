package Application.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import Application.Entities.User;
import Application.Exceptions.UnauthorizedException;
import Application.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	UserService us;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/enter/**", request.getServletPath());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, UnauthorizedException {

		String baererToken = request.getHeader("Authorization");

		if (baererToken == null || !baererToken.startsWith("Bearer ")) {
			throw new UnauthorizedException("ci sono stati dei Problemi , per favore effettua di nuovo il login");
		}

		String token = baererToken.substring(7);

		SecurityTool.validazioneToken(token);

		User user = us.findbyUserid(SecurityTool.estrazioneIdUtente(token));

		UsernamePasswordAuthenticationToken loggaUtente = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		loggaUtente.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(loggaUtente);

		filterChain.doFilter(request, response);
	}
}
