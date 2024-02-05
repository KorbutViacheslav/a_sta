package com.taskmanager.controller;


import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/read-task")
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(taskId);
        if (task != null) {
            request.setAttribute("task", task);
            var requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/read-task.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("message", "Task with ID " + taskId + " not found!");
            request.setAttribute("url", request.getContextPath() + "/read-task?id=" + taskId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            var requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
