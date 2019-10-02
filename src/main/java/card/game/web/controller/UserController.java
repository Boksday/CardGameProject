package card.game.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import card.game.web.dto.UserDto;
import card.game.web.mapper.UserMapper;

@RestController
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/getUsers")
	public List<UserDto> getUsers(){
		return userMapper.getAll();
	}
	
	@GetMapping("/loginValidCheck")
	public boolean loginCheck( @ModelAttribute UserDto loginUser ) {
		UserDto userDto = userMapper.getUser(loginUser);
		if( !(userDto.getId().equals(loginUser.getId() ))) {
			return false;
		}else {
			if( userDto.getPassword().equals(loginUser.getPassword()) ){
				return true;
			}
		}
		return false;
	}
	
	@GetMapping("/createUser")
	public void createUser(@ModelAttribute UserDto regDto) {
		userMapper.insertUser(regDto);
	}
	
	@GetMapping("/idCheck")
	public boolean idCheck(@ModelAttribute UserDto idDto) {
		if(userMapper.getUser(idDto) == null){
			return false;
		}else {
			return true;
		}
	}
}
