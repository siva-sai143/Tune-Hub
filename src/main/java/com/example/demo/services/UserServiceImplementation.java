package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository repo;

	
	public String addUser(Users user) {
		repo.save(user);
		return "user registerd succesfully";
	}


	@Override
	public boolean emailExist(String email) {
		if(repo.findByEmail(email)==null) {
			return false;
			
		}
		else {
		return true;
	}
	}


	@Override
	public boolean validateUser(String email,String password) {
		Users user=repo.findByEmail(email);
		if(password.equals(user.getPassword())){
		
		return true;
	}
		else {
			return false;
		}
	}


	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		return user.getRole();
	
	}


	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
	
	}


	@Override
	public void update(Users user) {
		repo.save(user);
		
	}

}
