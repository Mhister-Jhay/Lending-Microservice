package org.jhay.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jhay.application.dto.UserResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    private Long id;
    private Double amountBorrowed;
    private String purpose;
    private Integer durationInMonths;
    private Double amountToRepay;
    private Boolean isApproved;
    private UserResponse user;
    private UserResponse approvedBy;
}
