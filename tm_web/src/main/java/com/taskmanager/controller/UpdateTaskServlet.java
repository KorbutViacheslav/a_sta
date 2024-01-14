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

        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task1 = taskRepository.read(taskId);
        if(task1==null){
        request.setAttribute("message", "Task with ID " + taskId + " not found!");
        request.setAttribute("url", request.getContextPath() + "/edit-task?id=" + taskId);
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/error.jsp");
        requestDispatcher.forward(request, response);
        }else {
            request.setAttribute("task",task1);
            var requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/edit-task.jsp");
            requestDispatcher.forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}