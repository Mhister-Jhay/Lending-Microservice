package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double amountBorrowed;
    @Column(nullable = false)
    private String purpose;
    @Column(nullable = false)
    private Integer durationInMonths;
    @Column(nullable = false)
    private Double amountToRepay;
    @Column(nullable = false)
    private Double amountLeftToPay;
    @Column(nullable = false)
    private LocalDateTime paymentUpdated;
    @Column(nullable = false)
    private Boolean isApproved;
    @Column(nullable = false)
    private Boolean isPaymentInitiated;
    @Column(nullable = false)
    private Boolean isPaymentCompleted;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "approved_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User approvedBy;
}
