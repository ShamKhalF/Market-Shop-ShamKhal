package az.developia.MarketShopShamKhal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import az.developia.MarketShopShamKhal.Model.Authority;
import az.developia.MarketShopShamKhal.Model.User;
import az.developia.MarketShopShamKhal.repository.AuthorityRepository;
import az.developia.MarketShopShamKhal.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public List<Authority> findAllAuthorities(){
		return authorityRepository.findAll();
	}
	
	public User saveUser(User u) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		u.setPassword("{bcrypt}"+encoder.encode(u.getPassword()));
		u.setEnabled(1);
		Authority a = new Authority();
		a.setUsername(u.getUsername());
		a.setAuthority(u.getUsername() + ": no authorization yet: edit user's authorities");
		
		authorityRepository.save(a);
		return userRepository.save(u);
	}
	
	public User editUser(User u) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		u.setPassword("{bcrypt}"+encoder.encode(u.getPassword()));
		
		return userRepository.save(u);
	}
	
	public Authority editAuthority(Authority a) {
		
		return authorityRepository.save(a);
	}
	
	public Authority addAuthority(Authority a) {
		a.setId(null);
		return authorityRepository.save(a);
	}
	

	
	
	
	
	
}
