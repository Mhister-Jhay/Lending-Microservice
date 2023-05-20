package org.jhay.application.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSavedResponse {
    private Long id;
    private String fullName;
    private String email;
}
