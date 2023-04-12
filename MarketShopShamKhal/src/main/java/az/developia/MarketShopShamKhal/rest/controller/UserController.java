package az.developia.MarketShopShamKhal.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.MarketShopShamKhal.Model.Authority;
import az.developia.MarketShopShamKhal.Model.User;
import az.developia.MarketShopShamKhal.exception.MyUserExceptions;
import az.developia.MarketShopShamKhal.repository.AuthorityRepository;
import az.developia.MarketShopShamKhal.repository.UserRepository;
import az.developia.MarketShopShamKhal.service.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	@GetMapping(path = "/all-users")
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@GetMapping(path = "/all-authorities")
	public List<Authority> findAllAuthorities(){
		return userService.findAllAuthorities();
	}
	
	@PostMapping(path = "/save-user")
	public User saveUser(@RequestBody User u) throws Exception {
		
		Optional<User> userOptional = userRepository.findById(u.getUsername());
		if(userOptional.isPresent()) {
			throw new MyUserExceptions(u.getUsername() + ": adlı istifadəçi artıq mövcuddur.");
		}
		
		return userService.saveUser(u);
	}
	
	@PostMapping(path = "add-authority")
	public Authority addAuthority(@RequestBody Authority a) throws Exception {
		Optional<User> userOptional = userRepository.findById(a.getUsername());
		if(userOptional.isEmpty()) {
			throw new MyUserExceptions(a.getUsername() + ": adlı istifadəçi mövcud deyil.");
		}
		if(userOptional.get().getEnabled() == 0) {
			throw new MyUserExceptions(a.getUsername() + ": adlı istifadəçinin aktivliyi deaktiv edilib");
		}
		List<Authority> aList = authorityRepository.findAll();
		for (Authority authority : aList) {
			if(authority.getUsername().equals(a.getUsername()) && authority.getAuthority().equals(a.getAuthority())) {
				throw new MyUserExceptions(a.getUsername() + ": adlı istifadəçinin - " + a.getAuthority() + ": adlı hüququ artıq mövcuddur.");
			}
		}
		return userService.addAuthority(a);
	}
	
	@PutMapping(path = "/edit-authority")
	public Authority editAuthority(@RequestBody Authority a) throws Exception {
		
		Optional<Authority> finded = authorityRepository.findById(a.getId());
		if(finded.isEmpty()) {
			throw new MyUserExceptions("istifadəçi mövcud deyil");
		}
		a.setUsername(finded.get().getUsername());
		
		return userService.editAuthority(a);
	}
	
	@PutMapping(path = "/edit-user")
	public User editUser(@RequestBody User u) throws Exception {
		
		Optional<User> finded = userRepository.findById(u.getUsername());
		if(finded.isEmpty()) {
			throw new MyUserExceptions(u.getUsername() + ": adlı istifadəçi mövcud deyil.");
		}
		if(u.getEnabled() == null) {
			u.setEnabled(finded.get().getEnabled());
		}
		if(u.getPassword() == null) {
			u.setPassword(finded.get().getPassword());
		}
		
		return userService.editUser(u);
	}
	
	
	
	
}
