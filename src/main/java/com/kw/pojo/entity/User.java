package com.kw.pojo.entity;

import com.kw.common.enumration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String name;
    private Integer studentId;
    private Integer teacherId;
    private String username;
    private String password;
    private Role role;

}
