package com.example.examwcd.controller;

import com.example.examwcd.entity.Employee;
import com.example.examwcd.model.EmployeeModel;
import com.example.examwcd.model.MysqlEmployeeModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListServlet extends HttpServlet {
    private EmployeeModel employeeModel;
    @Override
    public void init() throws ServletException {
        employeeModel = new MysqlEmployeeModel();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeModel.findAll();
        if(employeeList == null) {
            employeeList = new ArrayList<>();
        }
        req.setAttribute("employeelist", employeeList);
        req.getRequestDispatcher("/client/view/list.jsp").forward(req, resp);
    }
}
