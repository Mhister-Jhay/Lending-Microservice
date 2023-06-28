package org.jhay.domain.service.employment;

import org.jhay.application.model.request.EmploymentRequest;
import org.jhay.application.model.response.EmploymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmploymentService {
    EmploymentResponse saveEmployment(EmploymentRequest employmentRequest);

    EmploymentResponse getUserEmployment();

    EmploymentResponse updateEmployment(Long employmentId, EmploymentRequest request);
}
