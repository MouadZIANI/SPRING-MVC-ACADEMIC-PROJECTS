package com.isi.services;


import com.isi.entities.Role;
import com.isi.entities.User;
import com.isi.exceptions.ResourceNotFoundException;
import com.isi.repositories.RoleRepository;
import com.isi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Page<User>  getAllUsers(Optional<Integer> pageNo, Integer pageSize, String sortBy)
    {
        if(pageNo.isPresent()){
            Pageable paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
            return userRepository.findAll(paging);
        }else{
            Pageable paging = PageRequest.of(0, pageSize, Sort.by(sortBy));
            return userRepository.findAll(paging);
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public void save(User user) {
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         user.setCreated(timestamp);
         userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
    	userRepository.deleteById(id);
    }

	@Override
	@Transactional
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<Role> findRoleByUserID(long id) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setId(id);
		return roleRepository.findByUsers(u);
	}
}
