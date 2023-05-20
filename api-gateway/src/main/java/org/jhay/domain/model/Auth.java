package org.jhay.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auth_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String accessToken;
    @Column(unique = true,nullable = false)
    private String assignedTo;
    @Column(nullable = false)
    private Date validTill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Auth auth = (Auth) o;
        return getId() != null && Objects.equals(getId(), auth.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
