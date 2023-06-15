package org.jhay.domain.service.employment;

import org.jhay.application.model.request.EmploymentRequest;
import org.jhay.application.model.response.EmploymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmploymentService {
    EmploymentResponse saveEmployment(Long userId, EmploymentRequest employmentRequest);

    EmploymentResponse getUserEmployment(Long userId);

    EmploymentResponse updateEmployment(Long userId, Long employmentId, EmploymentRequest request);
}
