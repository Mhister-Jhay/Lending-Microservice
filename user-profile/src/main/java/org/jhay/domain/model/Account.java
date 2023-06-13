package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String id;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false)
    private String bankCode;
    @Column(nullable = false)
    private String accountName;
    @Column(nullable = false,unique = true)
    private String accountNumber;
    @Column(nullable = false)
    private String createdAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ToString.Exclude
    private User user;

}
