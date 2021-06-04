package com.xing.entity;

import lombok.Data;

/**
 * @Description: TODO
 * @Author DXX
 * @Date 2021/6/1
 **/
@Data
public class UserTest {

    private String no;
    private String name;
    private String age;
    private String desc1;
    private String desc2;
    private String desc3;
    private String desc4;

    public UserTest() {
    }

    public UserTest(String no, String name, String age, String desc1, String desc2, String desc3, String desc4) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.desc3 = desc3;
        this.desc4 = desc4;
    }
}
