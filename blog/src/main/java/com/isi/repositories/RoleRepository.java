package com.isi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isi.entities.Role;
import com.isi.entities.User;
	@Repository
	public interface RoleRepository extends JpaRepository<Role, Long> {
		
		List<Role> findByUsers(User u);

}
