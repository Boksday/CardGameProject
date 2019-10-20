package card.game.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import card.game.web.dto.Todo;
import card.game.web.mapper.TodoMapper;

@RestController
public class TodoController {

	@Autowired
	private TodoMapper todoMapper;

	@GetMapping("/getTodos")
	public List<Todo> getTodos() {
		List<Todo> todos = todoMapper.getTodos();
		return todos;
	}

	@GetMapping("/addTodo")
	public int addTodo(@ModelAttribute Todo todo) {
		todoMapper.addTodo(todo);
		return todoMapper.getOneMaxTodo();
	}

	@GetMapping("/deleteTodo")
	public void deleteTodo(@ModelAttribute Todo todo) {
		todoMapper.deleteTodo(todo);
	}
	
	@GetMapping("/clickTodo")
	public void clickTodo(@ModelAttribute Todo todo) {
		todoMapper.clickTodo(todo);
	}
	
	@GetMapping("/completeTodo")
	public List<Todo> completeTodo() {
		return todoMapper.completeTodo();
	}
	
	@GetMapping("/notYetTodo")
	public List<Todo> notYetTodo() {
		return todoMapper.notYetTodo();
	}
	@GetMapping("/updateTodo")
	public void updateTodo(@ModelAttribute Todo todo) {
		todoMapper.updateTodo(todo);
	}
}
