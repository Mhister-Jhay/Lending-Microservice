package jhay.auth.domain.service.token;

import jhay.auth.common.event.ForgotPasswordEvent;
import jhay.auth.common.exception.TokenNotFoundException;
import jhay.auth.common.exception.UserAlreadyVerifiedException;
import jhay.auth.domain.model.User;
import jhay.auth.domain.model.VerificationToken;
import jhay.auth.domain.service.user.UserServiceImpl;
import jhay.auth.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenValidationServiceImpl implements TokenValidationService{
    private final VerificationTokenRepository tokenRepository;
    private final UserServiceImpl userService;
    private final ApplicationEventPublisher publisher;
    @Transactional
    @Override
    public String validateToken(String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if(verificationToken == null){
            throw new TokenNotFoundException(token);
        }
        User user = verificationToken.getUser();
        if(user.getIsEnabled()){
            throw new UserAlreadyVerifiedException(user.getEmail());
        }
        if(verificationToken.getExpirationTime().before(new Date())){
            return "Please click on the link to get a new verification mail : "+
                    "http://localhost:8080/auth/request-new-verification-token?email="+user.getEmail();
        }
        user.setIsEnabled(true);
        if(user.getIsEnabled()){
            return "Email Verified Successfully, Please proceed to Login.";
        }else{
            return "Invalid Token";
        }
    }
    @Transactional
    @Override
    public String requestNewVerificationToken(String email){
        User user = userService.getUserByEmail(email);
        if(user.getIsEnabled()){
            throw new UserAlreadyVerifiedException(email);
        }
        VerificationToken verificationToken =
                tokenRepository.findByUser(user);
        publisher.publishEvent(new ForgotPasswordEvent(user));
        tokenRepository.delete(verificationToken);
        return "Please check your mail for new Verification Link";
    }
    @Transactional
    @Override
    public String requestForgotPasswordToken(String email) {
        User user = userService.getUserByEmail(email);
        if (user.getIsEnabled()) {
            throw new UserAlreadyVerifiedException(email);
        }
        VerificationToken verificationToken =
                tokenRepository.findByUser(user);
        publisher.publishEvent(new ForgotPasswordEvent(user));
        tokenRepository.delete(verificationToken);
        return "Please check your mail for new Verification Link";
    }

    @Override
    public String validatePasswordToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if(verificationToken == null){
            throw new TokenNotFoundException(token);
        }
        if(verificationToken.getExpirationTime().before(new Date())){
            return "Link expired! Please go request for new Reset password Link";
        }
        return verificationToken.getUser().getEmail();
    }

    public void saveVerificationToken(String token, User user){
        VerificationToken verificationToken = new VerificationToken(token,user);
        tokenRepository.save(verificationToken);
    }
}
