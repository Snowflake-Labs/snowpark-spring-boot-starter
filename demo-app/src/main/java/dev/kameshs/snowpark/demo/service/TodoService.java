package dev.kameshs.snowpark.demo.service;

import com.snowflake.snowpark_java.Session;
import dev.kameshs.snowpark.demo.model.Todo;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
  private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

  final Session session;

  public TodoService(Session session) {
    this.session = session;
  }

  /**
   * @return list of customers
   */
  public List<Todo> listAll() {
    return Arrays.stream(
            session.table("TODOS").select("TITLE", "DESCRIPTION", "CATEGORY", "STATUS").collect())
        .map(
            row -> {
              Todo c = new Todo();
              c.setTitle(row.getString(0));
              c.setDescription(row.getString(1));
              c.setCategory(row.getString(2));
              c.setStatus(row.getBoolean(3));
              return c;
            })
        .toList();
  }
}
