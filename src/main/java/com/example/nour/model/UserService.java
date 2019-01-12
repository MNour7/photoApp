package com.example.nour.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nour.repository.AppUserRepository;

@Service
public class UserService implements UserDetailsService {
	
	public BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	private AppUserRepository appUserRepository;
	
	@Autowired
	public UserService(AppUserRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = employeeRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appUser = appUserRepository.findByEmail(email);
		if(appUser != null) {
			return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(),
					true,true,true,true, getAuthorities(appUser.getUserRoles()));
		}	
		
		throw new UsernameNotFoundException(email);
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (UserRole userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().getTile()));
		}
		
        return authorities;
    }
	
	public void saveUser(AppUser appUser) {
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		
		appUserRepository.save(appUser);
	}

}
