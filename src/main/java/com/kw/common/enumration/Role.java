package com.kw.common.enumration;

public enum Role {

    STUDENT,
    TEACHER;

    public String getDisplayName() {
        switch (this) {
            case STUDENT:
                return "学生";
            case TEACHER:
                return "老师";
            default:
                return "未知角色";
        }
    }
}
