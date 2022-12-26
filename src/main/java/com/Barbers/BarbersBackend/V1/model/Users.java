package com.Barbers.BarbersBackend.V1.model;

import com.Barbers.BarbersBackend.V1.enunms.Roles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String userName;

    private String fullName;

    private String email;

    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Roles type;
}
