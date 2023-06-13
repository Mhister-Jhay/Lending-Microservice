package org.jhay.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jhay.domain.enums.EmploymentStatus;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmploymentRequest {
    private String employmentStatus;
    private String position;
    private Double salary;
    private String company;
    private String companyAddress;
    private String companyContact;
    private String companyEmail;
}
