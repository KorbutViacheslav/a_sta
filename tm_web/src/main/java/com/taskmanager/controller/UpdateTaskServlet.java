package com.taskmanager.controller;



import com.taskmanager.model.Priority;
import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Task task;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: implement /edit-task doGet
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: implement /edit-task doPost
    }
}