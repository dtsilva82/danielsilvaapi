package br.edu.infnet.danielsilvaapi.dto;

public class RawgRating {
    // Este é o campo que contém a classificação textual (ex: "recommended")
    private String title; 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}