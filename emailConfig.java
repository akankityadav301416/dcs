package com.example.model;

public class EmailConfig {
    private String type;
    private String value;
    private String appPerimeter;
    private String subPerimeter;
    private String roleName;

    // Getters and Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAppPerimeter() {
        return appPerimeter;
    }

    public void setAppPerimeter(String appPerimeter) {
        this.appPerimeter = appPerimeter;
    }

    public String getSubPerimeter() {
        return subPerimeter;
    }

    public void setSubPerimeter(String subPerimeter) {
        this.subPerimeter = subPerimeter;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
