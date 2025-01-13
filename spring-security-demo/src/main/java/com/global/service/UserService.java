package com.global.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.entity.AppUser;
import com.global.repository.UserRepo;
import com.global.security.AppUserDetail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RequiredArgsConstructor
@Log4j2
@Service
public class UserService implements UserDetailsService{
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public List<AppUser> findAll(){
		return userRepo.findAll();
	}
	
	public AppUser findById(Long id){
		return userRepo.findById(id).orElse(null);
	}
	
	public AppUser insert(AppUser user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    AppUser user = userRepo.findByUserName(username)
	        .orElseThrow(() -> new UsernameNotFoundException("This user -> " + username + " is not exiested"));
	    return new AppUserDetail(user);
	}

}
