package card.game.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import card.game.web.dto.Todo;

@Mapper
public interface TodoMapper {
	public List<Todo> getTodos();
	public List<Todo> completeTodo();
	public List<Todo> notYetTodo();
	public int getOneMaxTodo();
	public void addTodo(Todo todo);
	public void updateTodo(Todo todo);
	public void deleteTodo(Todo todo);
	public void clickTodo(Todo todo);
}
