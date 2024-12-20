package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentHomework implements Serializable {
    private Integer studentHomeworkId;
    private Integer studentId;
    private Integer homeworkId;
    private LocalDateTime submitTime;
}
