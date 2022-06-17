package com.example.examwcd.model;

import com.example.examwcd.constant.SqlConstant;
import com.example.examwcd.entity.Employee;
import com.example.examwcd.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlEmployeeModel implements  EmployeeModel{
    @Override
    public boolean save(Employee obj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SqlConstant.EMPLOYEE_INSERT);
            preparedStatement.setString(1, obj.getFullName());
            preparedStatement.setString(2, obj.getBirthday());
            preparedStatement.setString(3, obj.getAddress());
            preparedStatement.setString(4, obj.getPosition());
            preparedStatement.setString(5, obj.getDepartment());
            preparedStatement.execute();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.EMPLOYEE_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String birthday = rs.getString("birthday");
                String address = rs.getString("address");
                String position = rs.getString("position");
                String department = rs.getString("department");

                Employee employee = new Employee(id,fullName,birthday,address,position,department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
