package com.mybooking.users;

import com.mybooking.reservations.ReservationEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"reservations"})
@ToString(exclude = {"reservations"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MYBOOKING_USER")
public class UserEntity {
    @Id
    @GenericGenerator(
            name = "user_id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MYBOOKING_USER_ID_SEQ")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MYBOOKING_USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<RoleEntity> roles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ReservationEntity> reservations;
}
