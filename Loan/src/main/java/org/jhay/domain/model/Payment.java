package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String reference;
    @Column(nullable = false)
    private Double amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payee_id", referencedColumnName = "id")
    @ToString.Exclude
    private User paidTo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    @ToString.Exclude
    private User paidBy;
    private LocalDate paid_at;
    private LocalDate created_at;
    private String channel;

}
