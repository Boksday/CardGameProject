package card.game.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import card.game.web.dto.UserDto;

@Mapper
public interface UserMapper {
	List<UserDto> getAll();
	UserDto getUser(UserDto userDto);
	UserDto getUserForLogin(UserDto userDto);
	void insertUser(UserDto userDto);
	
	void updateUser(UserDto userDto);
	void deleteUser(UserDto userDto);
	
}
