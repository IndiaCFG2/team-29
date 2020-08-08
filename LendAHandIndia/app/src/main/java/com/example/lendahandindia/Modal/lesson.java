package com.example.lendahandindia.Modal;

public class lesson
{
    String chapter,grade,description,teacher,url;
    public lesson()
    {

    }

    public lesson(String chapter, String grade, String description, String teacher, String url) {
        this.chapter = chapter;
        this.grade = grade;
        this.description = description;
        this.teacher = teacher;
        this.url = url;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
