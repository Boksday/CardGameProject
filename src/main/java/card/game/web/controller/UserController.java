package card.game.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import card.game.web.dto.UserDto;
import card.game.web.mapper.UserMapper;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/getUsers")
	public List<UserDto> getUsers(){
		return userMapper.getAll();
	}
	
	@PostMapping("/loginValidCheck")
	public UserDto loginCheck( @RequestBody  UserDto loginUser, HttpServletRequest req  ) {
		UserDto userDto = userMapper.getUser(loginUser);
		System.out.println(loginUser);
		if( !(userDto.getId().equals(loginUser.getId() ))) {
			return null;
		}else {
			if( userDto.getPassword().equals(loginUser.getPassword()) ){
				userDto.setPassword("");
				req.getSession().setAttribute("user", userDto);
				System.out.println(userDto);
				return userDto;
			}
		}
		return null;
	}
	
	@PostMapping("/createUser")
	public void createUser(@RequestBody UserDto regDto) {
		System.out.println(regDto);
		userMapper.insertUser(regDto);
	}
	
	@GetMapping("/idCheck")
	public boolean idCheck(@ModelAttribute UserDto idDto) {
		System.out.println(idDto);
		if(userMapper.getUser(idDto) == null){
			return false;
		}else {
			return true;
		}
	}
}
