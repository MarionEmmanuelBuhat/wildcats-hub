package com.example.wildcats_hub;

public class TaskModel {
    String taskId;
    String taskName;
    String description;
    String priorityLevel;
    String tags;
    String dueDate;
    String dueTime;

    public TaskModel(String taskId, String taskName, String description, String priorityLevel, String tags, String dueDate, String dueTime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.tags = tags;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getTags() {
        return tags;
    }

    public String getDueTime() {
        return dueTime;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTaskId() {
        return taskId;
    }
}
