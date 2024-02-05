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
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            task = taskRepository.read(id);
            if (task == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                request.setAttribute("message", "Task with ID " + id + " not found!");
                request.setAttribute("url", "/edit-task");
                request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            } else {
                request.setAttribute("task", task);
                request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "You must enter the task id!");
            request.setAttribute("url", "/edit-task");
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        task.setTitle(request.getParameter("title"));
        task.setPriority(Priority.valueOf(request.getParameter("priority")));
        taskRepository.update(task, task.getId());
        response.sendRedirect("/tasks-list");
    }
}