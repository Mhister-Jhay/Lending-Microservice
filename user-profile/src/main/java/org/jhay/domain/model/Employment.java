package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.jhay.domain.enums.EmploymentStatus;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employment")
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    private String position;
    private Double salary;
    private String company;
    private String companyAddress;
    private String companyContact;
    private String companyEmail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

}
