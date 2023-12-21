package com.familymanagement.springboot.myfamilyapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	
	static { 
		todos.add(new Todo(++todosCount, "lamuril", "Static Pediatrician", 
					LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "lamuril", "Static Dentist", 
					LocalDate.now().plusYears(2), false));		
		todos.add(new Todo(++todosCount, "lamuril", "Static Vaccines", 
					LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username){ 
		// If username matches, then it matches and list the todo list
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();		
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		// if todo.getID() == id then delete todo item
		// Does this todo have a matching id in the todo list, remove if it does
		// checks id against all ids in bean
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		// checks all todos one by one like a for loop, functional programming
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
	}
}
