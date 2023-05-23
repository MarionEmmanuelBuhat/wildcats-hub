package com.example.wildcats_hub;

public class TaskModel {
    String taskName;
    String priorityLevel;

    public TaskModel(String taskName, String color) {
        this.taskName = taskName;
        this.priorityLevel = color;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }
}
