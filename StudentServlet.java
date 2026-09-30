package com.example.studentmvc.controller;

import com.example.studentmvc.dao.StudentDAO;
import com.example.studentmvc.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO dao;

    @Override
    public void init() {
        dao = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("students", dao.findAll());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to load students", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                dao.save(readStudent(request));
            } else if ("update".equals(action)) {
                Student s = readStudent(request);
                s.setId(Integer.parseInt(request.getParameter("id")));
                dao.update(s);
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(request.getParameter("id")));
            }
            response.sendRedirect(request.getContextPath() + "/students");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Student operation failed", e);
        }
    }

    private Student readStudent(HttpServletRequest request) {
        return new Student(
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("course")
        );
    }
}
