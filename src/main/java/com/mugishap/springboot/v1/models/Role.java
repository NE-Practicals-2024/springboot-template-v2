package com.mugishap.springboot.v1.models;


import com.mugishap.springboot.v1.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    @Column(name = "description")
    private String description;

    public Role(ERole name, String description) {
        this.name = name;
        this.description = description;
    }
}
