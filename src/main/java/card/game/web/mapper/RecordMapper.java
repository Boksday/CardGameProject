package card.game.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import card.game.web.dto.RecordDto;
import card.game.web.dto.UserDto;

@Mapper
public interface RecordMapper {
	public void insertRecord(RecordDto recordDto);
	public List<RecordDto> selectRecordsByTime();
	public List<RecordDto> selectRecordsByTries();
	public RecordDto personalRecord(UserDto userDto);
}
