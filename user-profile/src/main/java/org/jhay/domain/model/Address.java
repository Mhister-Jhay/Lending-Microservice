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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String landmark;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String postalCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ToString.Exclude
    private User user;
}
