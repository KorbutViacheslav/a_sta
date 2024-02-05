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
            Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("message", request.getParameter("id"));
            request.setAttribute("url", "/edit-task");
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
        int id = Integer.parseInt(request.getParameter("id"));
        if (taskRepository.read(id) == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("message", id);
            request.setAttribute("url", "/edit-task");
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        } else {
            task = taskRepository.read(id);
            request.setAttribute("task", task);
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        task.setTitle(request.getParameter("title"));
        task.setPriority(Priority.valueOf(request.getParameter("priority")));
        taskRepository.update(task, task.getId());
        response.sendRedirect("/tasks-list");
    }
}