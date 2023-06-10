package jhay.auth.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jhay.auth.domain.model.User;
import jhay.auth.domain.service.user.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthProvider authProvider;
    private final UserDetailServiceImpl userService;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String userEmail;
        String accessToken;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        accessToken = authHeader.substring(7);
        userEmail = authProvider.extractUsername(accessToken);
        if(userEmail!=null&& SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =  userService.loadUserByUsername(userEmail);
            var isTokenValid = jwtTokenRepository.findJwtTokenByAccessToken(accessToken)
                    .map(t-> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (authProvider.isTokenValid(accessToken, userDetails) && isTokenValid){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
