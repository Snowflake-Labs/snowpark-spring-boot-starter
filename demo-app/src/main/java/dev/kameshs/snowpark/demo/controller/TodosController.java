package dev.kameshs.snowpark.demo.controller;

import dev.kameshs.snowpark.demo.model.Todo;
import dev.kameshs.snowpark.demo.service.TodoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodosController {

  private static final Logger LOG = LoggerFactory.getLogger(TodosController.class);

  final TodoService todoService;

  public TodosController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping({"/", "/todos/list"})
  public String allTodos(Model model) {
    List<Todo> todos = todoService.listAll();
    LOG.info("Got {} Todos", todos.size());
    model.addAttribute("todos", todos);
    return "todos";
  }
}
