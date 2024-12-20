package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes implements Serializable {
    private Integer classId;
    private String className;
    private Integer studentId;
    private Integer teacherId;
}
