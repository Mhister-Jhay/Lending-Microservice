package jhay.auth.common.exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String token) {
        super("Verification Time Elapsed, Expired Token :"+token);
    }
}
