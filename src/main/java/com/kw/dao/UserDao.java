package com.kw.dao;

import cn.hutool.core.bean.BeanUtil;
import com.kw.common.enumration.Role;
import com.kw.pojo.dto.UserDto;
import com.kw.pojo.entity.User;
import com.kw.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDao() {

    }


    public User getUserById(int id)  {
        System.out.println("User fetched successfully");
        try {
            connection = connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id =?");
            preparedStatement.setInt(1, id);
            User user = new User();
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                BeanUtil.copyProperties(resultSet, user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public User getUserByUsername(String username) {
        try {
            connection = connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Username =?");
            preparedStatement.setString(1, username);
            User user = new User();
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("UserId"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                if (resultSet.getString("StudentId") == null) {
                    user.setRole(Role.TEACHER);
                    user.setTeacherId(Integer.parseInt(resultSet.getString("TeacherId")));
                } else {
                    user.setRole(Role.STUDENT);
                    user.setStudentId(Integer.parseInt(resultSet.getString("StudentId")));
                }
            }

            return user;
        } catch (Exception e) {
            return null;
        }

    }

        public ArrayList<User> getAllUsers()  {
        try {
            connection = connection = JDBCUtil.getConnection();
            System.out.println("All users fetched successfully");
            ArrayList<User> users = new ArrayList<>();
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("UserId"));
                user.setUsername(resultSet.getString("Username"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                if (resultSet.getString("StudentId") == null) {
                    user.setRole(Role.TEACHER);
                    user.setTeacherId(Integer.parseInt(resultSet.getString("TeacherId")));
                } else {
                    user.setRole(Role.STUDENT);
                    user.setStudentId(Integer.parseInt(resultSet.getString("StudentId")));
                }
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(User user) throws Exception {

        // 检查用户名是否存在
        User userByUsername = getUserByUsername(user.getUsername());
            if (userByUsername!= null) {
            throw new Exception("Username already exists");
        }


            connection = connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO users (Username, Password,Name,StudentID," +
                    "Role) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getStudentId().toString());
            preparedStatement.setString(5, user.getRole().getDisplayName());
            preparedStatement.executeUpdate();
            System.out.println("User saved successfully");



    }
    public void saveUser(UserDto userDto) throws Exception {

        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        // 检查用户名是否存在
        User userByUsername = getUserByUsername(user.getUsername());
        if (userByUsername!= null) {
            throw new Exception("Username already exists");
        }


        connection = connection = JDBCUtil.getConnection();
        preparedStatement = connection.prepareStatement("INSERT INTO users (Username, Password,Name,StudentID," +
                "Role) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getName());
        preparedStatement.setString(4, user.getStudentId().toString());
        preparedStatement.setString(5, user.getRole().getDisplayName());
        preparedStatement.executeUpdate();

        // 插入class表
        preparedStatement = connection.prepareStatement("INSERT INTO class (ClassID, ClassName, TeacherID,StudentID) VALUES (?,?,?,?)");
        preparedStatement.setString(1, userDto.getClassId().toString());
        preparedStatement.setString(2, "教学" + userDto.getClassId().toString()+"班");
        preparedStatement.setString(3, userDto.getTeacherId().toString());
        preparedStatement.setString(4, userDto.getStudentId().toString());

        System.out.println("User saved successfully");



    }

    public void deleteUser(int id)  {
        try {
            connection = connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id =?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void updateUser(User user)  {
        // TODO: Implement updateUser() method
        try {
            connection = connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE users SET Username = ?, Password = ?, Name = ?, StudentID = ?, TeacherID = ?, Role = ? WHERE id = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getStudentId().toString());
            preparedStatement.setString(5, user.getTeacherId().toString());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.setInt(7, user.getUserId());
            preparedStatement.executeUpdate();

            System.out.println("User updated successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void closeConnection() throws Exception {
        JDBCUtil.closeConnection(connection, preparedStatement);
    }

}
