package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade implements Serializable {
    private Integer gradeId;
    private Integer studentHomeworkId;
    private Integer questionId;
    private BigDecimal score;
    private String comment;
}
