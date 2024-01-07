package com.taskmanager.model;

import lombok.Getter;

@Getter
public class Task {
    private int id;
    private String title;
    private Priority priority;

    private static int counter = 1;

    public Task() {
        id = counter++;
    }

    public Task(int id) {
        this.id = id;
    }

    public Task(String title, Priority priority) {
        this.title = title;
        this.priority = priority;
        id = counter++;
    }

    public Task(int taskId, String title, Priority priority) {
        this.id = taskId;
        this.title = title;
        this.priority = priority;

        if (taskId >= counter) {
            counter = taskId + 1;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
