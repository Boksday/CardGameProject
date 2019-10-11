package card.game.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import card.game.web.dto.RecordDto;
import card.game.web.dto.UserDto;
import card.game.web.mapper.RecordMapper;

@RestController
public class RecordController {
	@Autowired
	RecordMapper recordMapper;
	
	@PostMapping("/insertRecord")
	public void insertRecord(@RequestBody RecordDto recordDto) {
		System.out.println(recordDto);
		recordMapper.insertRecord(recordDto);
	}
	
	@PostMapping("/selectRecordsByTime")
	public List<RecordDto> selectRecordsByTime() {
		return recordMapper.selectRecordsByTime();
	}
	
	@PostMapping("/selectRecordsByTries")
	public List<RecordDto> selectRecordsByTries() {
		return recordMapper.selectRecordsByTries();
	}
	
	@PostMapping("/personalRecord")
	public RecordDto personalRecord(@RequestBody UserDto userDto) {
		System.out.println(userDto+"///////");
		System.out.println( recordMapper.personalRecord(userDto) );
		RecordDto returnType = recordMapper.personalRecord(userDto);
		System.out.println(returnType+"/////////////");
		return returnType;
	}
}
