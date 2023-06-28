package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.jhay.domain.enums.Gender;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private Boolean isAccountSaved;
    @Column(nullable = false)
    private Boolean isAddressSaved;
    @Column(nullable = false)
    private Boolean isEmploymentSaved;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Loan> loans;
}

