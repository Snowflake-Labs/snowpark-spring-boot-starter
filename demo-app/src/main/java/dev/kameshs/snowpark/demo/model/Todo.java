package dev.kameshs.snowpark.demo.model;

import lombok.Data;

@Data
public class Todo {
  private String title;
  private String description;
  private String category;
  private boolean status;
}
