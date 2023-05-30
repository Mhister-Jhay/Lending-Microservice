package jhay.auth.common.event;

import jhay.auth.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class ForgotPasswordEvent extends ApplicationEvent {
    private User user;
    public ForgotPasswordEvent(User user) {
        super(user);
        this.user = user;
    }
}
