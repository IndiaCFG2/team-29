package com.example.lendahandindia.Modal;

public class DoubtsFourmModal {

    private String id;
    private String doubts;

    public DoubtsFourmModal() {
    }

    public DoubtsFourmModal(String id, String doubts) {
        this.id = id;
        this.doubts = doubts;
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
