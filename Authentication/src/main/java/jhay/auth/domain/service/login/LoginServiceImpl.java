package jhay.auth.domain.service.login;

import jakarta.servlet.http.HttpServletRequest;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.application.model.LoginRequest;
import jhay.auth.common.event.ForgotPasswordEvent;
import jhay.auth.common.exception.BadCredentialsException;
import jhay.auth.common.exception.UserNotVerifiedException;
import jhay.auth.common.security.jwt.JwtAuthProvider;
import jhay.auth.common.security.jwt.JwtToken;
import jhay.auth.common.security.jwt.JwtTokenRepository;
import jhay.auth.common.utils.EmailUtils;
import jhay.auth.domain.model.User;
import jhay.auth.domain.model.VerificationToken;
import jhay.auth.domain.service.notification.NotificationService;
import jhay.auth.domain.service.user.UserServiceImpl;
import jhay.auth.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserServiceImpl userService;
    private final NotificationService notificationService;
    private final JwtAuthProvider authProvider;
    private final JwtTokenRepository jwtTokenRepository;
    private final ApplicationEventPublisher publisher;
    private final JwtTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest){
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if(!user.getIsEnabled()){
            throw new UserNotVerifiedException(user.getEmail());
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new BadCredentialsException("Wrong password, Check enter your correct password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),user.getPassword());
        String accessToken = authProvider.generateToken(authentication);
        JwtToken jwtToken = new JwtToken(accessToken);
        if(tokenRepository.existsByUser(user)){
            JwtToken existingToken = tokenRepository.findByUser(user);
            existingToken.setAccessToken(jwtToken.getAccessToken());
            existingToken.setExpiresAt(jwtToken.getExpiresAt());
            existingToken.setGeneratedAt(jwtToken.getGeneratedAt());
            AuthResponse authResponse = new AuthResponse(jwtTokenRepository.save(existingToken));
            notificationService.sendLoginMessage(authResponse);
            return authResponse;
        }else{
            jwtToken.setUser(user);
            AuthResponse authResponse = new AuthResponse(jwtTokenRepository.save(jwtToken));
            notificationService.sendLoginMessage(authResponse);
            return authResponse;
        }
    }

    @Override
    public String forgotPassword(String email, HttpServletRequest request){
        User user = userService.getUserByEmail(email);
        publisher.publishEvent(new ForgotPasswordEvent(user, EmailUtils.applicationUrl(request)));
        return "Please Check your mail for new Password reset Link";
    }

    @Override
    public String resetPassword(String email, String password){
        User user = userService.getUserByEmail(email);
        VerificationToken token = verificationTokenRepository.findByUser(user);
        if(token == null || !token.getExpirationTime().before(new Date())){
            throw new IllegalStateException("Invalid Reset Password Token");
        }
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        return "Password Changed, Proceed to Login";
    }
}
