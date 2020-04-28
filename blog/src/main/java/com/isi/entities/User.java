package com.isi.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Email(message = "please add a valid email")
    @NotEmpty(message = "please fill the email")
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    @NotEmpty(message = "please fill the password")
    private String password;

    @Column(name="created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name="modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Article> articles;
    
    @ManyToMany()
    @JoinTable(name="users_roles", joinColumns={@JoinColumn(referencedColumnName="id")}
    , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    private List<Role> roles;
    
    @Transient
    private Boolean used;
    
    public User(long id) {
        this.id=id;
    }
}
