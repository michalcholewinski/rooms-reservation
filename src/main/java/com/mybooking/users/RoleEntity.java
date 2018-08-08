package com.mybooking.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
@Entity(name = "MYBOOKING_ROLE")
public class RoleEntity {

    @Id
    @GenericGenerator(
        name = "role_id_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "MYBOOKING_ROLE_ID_SEQ")
        }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_generator")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;
}
