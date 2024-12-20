package com.kw.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework implements Serializable {
            private Integer homeworkId;
            private Integer classId;
            private LocalDateTime publishDate;
            private LocalDateTime deadline;

}
