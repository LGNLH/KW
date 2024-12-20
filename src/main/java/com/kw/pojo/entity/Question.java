package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    private Integer questionId;
    private Integer homeworkId;
    private String content;
    private String StandardAnswer;
    private String requirements;
    private String attachment;
}
