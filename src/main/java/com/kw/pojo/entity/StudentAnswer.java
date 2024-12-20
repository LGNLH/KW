package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswer implements Serializable {
    private Integer answerId;
    private Integer studentHomeworkId;
    private Integer questionId;
    private String aContent;
    private String attachment;
}
