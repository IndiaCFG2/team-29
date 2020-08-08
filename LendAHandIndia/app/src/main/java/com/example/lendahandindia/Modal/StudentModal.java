package com.example.lendahandindia.Modal;

public class StudentModal {

    private String  classname;
    private String email;
    private String id;
    private String password;
    private String phonenumber;

    public StudentModal() {
    }

    public StudentModal(String classname, String email, String id, String password, String phonenumber) {
        this.classname = classname;
        this.email = email;
        this.id = id;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
