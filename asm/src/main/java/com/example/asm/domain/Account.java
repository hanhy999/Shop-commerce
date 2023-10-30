package com.example.asm.domain;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @Column(length = 200, nullable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;
    @Column(length = 200, nullable = false)
    private String fullName;
    @Column(length = 200, nullable = false)
    private String email;
    @Column(length = 200, nullable = false)
    private String photo;

    @Column(length = 200, nullable = false)
    private boolean activated;
    @Column(length = 200, nullable = false)
    private boolean admin;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "account",  fetch = FetchType.LAZY,cascade = { CascadeType.ALL })
    Set<Order> orders;


    


    // Set<Orders> orders;

}
