package card.game.web.dto;

import lombok.Data;

@Data
public class UserDto {
	private String id;
	private String nickName;
	private String password;
	private String role;
}
