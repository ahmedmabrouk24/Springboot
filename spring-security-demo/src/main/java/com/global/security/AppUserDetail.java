package com.global.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.global.entity.AppUser;

@Component
public class AppUserDetail implements UserDetails{

	private long id;
	private String fullName;
	private String userName;
	private String password;
	private List<GrantedAuthority> grantedAuthorities;
	
	
	public AppUserDetail() {
		super();
	}
	
	public AppUserDetail(AppUser user) {
		super();
		this.id = user.getId();
		this.fullName = user.getFullName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	    if (user.getRoles() != null && !user.getRoles().isEmpty()) {
	        user.getRoles().forEach(role -> {
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        });
	    }
	    this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	public long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	
	public boolean isAccountNonLocked() {
		return true;
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return true;
	}
}
