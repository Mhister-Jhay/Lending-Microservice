package org.jhay.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {
    private Double amountBorrowed;
    private String purpose;
    private Integer durationInMonths;
}
