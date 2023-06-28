package org.jhay.domain.service.employment;

import lombok.RequiredArgsConstructor;
import org.jhay.application.model.request.EmploymentRequest;
import org.jhay.application.model.response.EmploymentResponse;
import org.jhay.application.model.response.MessageResponse;
import org.jhay.application.model.response.UserResponse;
import org.jhay.common.exceptions.EmploymentAlreadyExistException;
import org.jhay.common.exceptions.EmploymentNotFoundException;
import org.jhay.common.exceptions.UnauthorizedException;
import org.jhay.common.exceptions.UserNotFoundException;
import org.jhay.domain.enums.EmploymentStatus;
import org.jhay.domain.model.Employment;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.EmploymentRepository;
import org.jhay.domain.repository.UserRepository;
import org.jhay.domain.service.notification.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService{
    private final EmploymentRepository employmentRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Override
    public EmploymentResponse saveEmployment(Long userId, EmploymentRequest employmentRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        if(employmentRepository.existsByUser(user)){
            throw new EmploymentAlreadyExistException("Employment Record is already saved");
        }
        Employment employment = employmentRepository.save(Employment.builder()
                        .employmentStatus(EmploymentStatus.fromDisplayName(employmentRequest.getEmploymentStatus()))
                        .position(employmentRequest.getPosition())
                        .company(employmentRequest.getCompany())
                        .salary(employmentRequest.getSalary())
                        .companyEmail(employmentRequest.getCompanyEmail())
                        .companyContact(employmentRequest.getCompanyContact())
                        .companyAddress(employmentRequest.getCompanyAddress())
                        .build());
        notificationService.sendEmploymentMessage(MessageResponse.builder()
                .userId(user.getId())
                .message("Employment Saved Successfully")
                .build());
        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        return EmploymentResponse.builder()
                .id(employment.getId())
                .employmentStatus(employment.getEmploymentStatus().name())
                .position(employment.getPosition())
                .salary(employment.getSalary())
                .company(employment.getCompany())
                .companyAddress(employment.getCompanyAddress())
                .companyContact(employment.getCompanyContact())
                .companyEmail(employment.getCompanyEmail())
                .userResponse(userResponse)
                .build();
    }
    @Cacheable(cacheNames = "employment-record", key = "#userId")
    @Override
    public EmploymentResponse getUserEmployment(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        Employment employment = employmentRepository.findByUser(user)
                .orElseThrow(()-> new EmploymentNotFoundException("User has no employment record"));
        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        return EmploymentResponse.builder()
                .id(employment.getId())
                .employmentStatus(employment.getEmploymentStatus().name())
                .position(employment.getPosition())
                .salary(employment.getSalary())
                .company(employment.getCompany())
                .companyAddress(employment.getCompanyAddress())
                .companyContact(employment.getCompanyContact())
                .companyEmail(employment.getCompanyEmail())
                .userResponse(userResponse)
                .build();
    }
    @CachePut(cacheNames = "employment-record", key = "#userId")
    @Override
    public EmploymentResponse updateEmployment(Long userId, Long employmentId, EmploymentRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        Employment employment = employmentRepository.findById(employmentId)
                .orElseThrow(()-> new EmploymentNotFoundException("User has no employment record"));
        if(!employment.getUser().equals(user)){
            throw new UnauthorizedException("User has no access to these records");
        }
        employment.setEmploymentStatus(EmploymentStatus.fromDisplayName(request.getEmploymentStatus()));
        employment.setPosition(request.getPosition());
        employment.setSalary(request.getSalary());
        employment.setCompany(request.getCompany());
        employment.setCompanyContact(request.getCompanyContact());
        employment.setCompanyAddress(request.getCompanyAddress());
        employment.setCompanyEmail(request.getCompanyEmail());
        Employment editedEmployment = employmentRepository.save(employment);
        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        return EmploymentResponse.builder()
                .id(editedEmployment.getId())
                .employmentStatus(editedEmployment.getEmploymentStatus().name())
                .position(editedEmployment.getPosition())
                .salary(editedEmployment.getSalary())
                .company(editedEmployment.getCompany())
                .companyAddress(editedEmployment.getCompanyAddress())
                .companyContact(editedEmployment.getCompanyContact())
                .companyEmail(editedEmployment.getCompanyEmail())
                .userResponse(userResponse)
                .build();
    }
}
