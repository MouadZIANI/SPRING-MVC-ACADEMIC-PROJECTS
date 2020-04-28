package com.isi.services;

import com.isi.entities.Role;
import com.isi.entities.User;
import com.isi.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;


public interface UserService {


    public Page<User> getAllUsers(Optional<Integer> pageNo, Integer pageSize, String sortBy);
    public List<User> getAllUsers();

    User findById(long id) throws ResourceNotFoundException;

    void save(User user);

    void deleteById(long id);
    
	User findUserByEmailAndPassword(String email, String password);
	List<Role> findRoleByUserID(long id);

}
