package com.example.demo.services;

import com.example.demo.entity.Users;

public interface UserService {
	 public  String addUser(Users user);
	 public boolean emailExist(String email);
	 public boolean validateUser(String email,String Password);
	 public String getRole(String email);
	public Users getUser(String email);
	public void update(Users user);

}
