package org.jhay.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentResponse {
    private Long id;
    private String employmentStatus;
    private String position;
    private Double salary;
    private String company;
    private String companyAddress;
    private String companyContact;
    private String companyEmail;
    private UserResponse userResponse;
}
