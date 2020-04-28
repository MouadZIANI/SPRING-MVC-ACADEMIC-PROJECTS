package com.isi.entities;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "roles")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String role;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private List<User> users;
}
