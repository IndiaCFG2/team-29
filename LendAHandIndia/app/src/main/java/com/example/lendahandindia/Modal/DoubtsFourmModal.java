package com.example.lendahandindia.Modal;

public class DoubtsFourmModal {

    private String id;
    private String doubts;
    private String answer;
    private String name;

    public DoubtsFourmModal() {
    }

    public DoubtsFourmModal(String id, String doubts, String answer, String name) {
        this.id = id;
        this.doubts = doubts;
        this.answer = answer;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoubts() {
        return doubts;
    }

    public void setDoubts(String doubts) {
        this.doubts = doubts;
    }
}
