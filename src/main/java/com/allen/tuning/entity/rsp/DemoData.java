package com.allen.tuning.entity.rsp;

/**
 * @author rui.xiong
 * @date 2020-07-07 17:43
 */
public class DemoData extends AbstractData{

    private static final long serialVersionUID = -8566721085007099478L;
    private String name="haha";
    private String age="18";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
