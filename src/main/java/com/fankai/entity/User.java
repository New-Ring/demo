package com.fankai.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 编号 */
    private long id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private int age;
}
